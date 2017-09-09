package work.beltran.discogsbrowser.app

import android.app.Application

import work.beltran.discogsbrowser.BuildConfig
import work.beltran.discogsbrowser.api.DiscogsServiceBuilderWithKey
import work.beltran.discogsbrowser.api.DiscogsServiceBuilderWithLogin
import work.beltran.discogsbrowser.api.LoginServiceBuilder
import work.beltran.discogsbrowser.app.di.ApiComponent
import work.beltran.discogsbrowser.app.di.AppComponent
import work.beltran.discogsbrowser.app.di.DaggerApiComponent
import work.beltran.discogsbrowser.app.di.DaggerAppComponent
import work.beltran.discogsbrowser.app.di.DaggerLoginComponent
import work.beltran.discogsbrowser.app.di.LoginComponent
import work.beltran.discogsbrowser.app.di.modules.ContextModule
import work.beltran.discogsbrowser.app.di.modules.DiscogsModule
import work.beltran.discogsbrowser.app.di.modules.LoginModule

class App : Application() {
    lateinit var apiComponent: ApiComponent
    lateinit var appComponent: AppComponent
    lateinit var loginComponent: LoginComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .contextModule(ContextModule(this))
                .build()
        loginComponent = DaggerLoginComponent
                .builder()
                .contextModule(ContextModule(this))
                .loginModule(
                        LoginModule(
                                LoginServiceBuilder(
                                        BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME)))
                .build()
        val settings = appComponent.settings
        if (BuildConfig.API_KEY.isNotEmpty()) {
            initApiComponentWithApiKey()
        } else if (!settings.userToken.isEmpty() && !settings.userSecret.isEmpty()) {
            initApiComponent(settings.userToken, settings.userSecret)
        }
    }

    private fun initApiComponentWithApiKey() {
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(ContextModule(this))
                .discogsModule(
                        DiscogsModule(
                                DiscogsServiceBuilderWithKey(BuildConfig.API_KEY,
                                        BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME)))
                .build()
    }

    fun initApiComponent(userToken: String, userSecret: String) {
        apiComponent = DaggerApiComponent
                .builder()
                .contextModule(ContextModule(this))
                .discogsModule(
                        DiscogsModule(
                                DiscogsServiceBuilderWithLogin(
                                        BuildConfig.API_CONSUMER_KEY,
                                        BuildConfig.API_CONSUMER_SECRET,
                                        userToken,
                                        userSecret,
                                        BuildConfig.APPLICATION_ID + "/" + BuildConfig.VERSION_NAME)))
                .build()
    }
}

