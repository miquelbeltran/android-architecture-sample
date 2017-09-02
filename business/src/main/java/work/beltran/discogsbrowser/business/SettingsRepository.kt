package work.beltran.discogsbrowser.business

interface SettingsRepository {
    fun storeUserToken(userToken: String)
    fun storeUserSecret(userSecret: String)
    fun getUserSecret(): String
    fun getUserToken(): String
    fun setUsername(username: String)
    fun getUsername(): String
}