package work.beltran.discogsbrowser.ui.di.modules;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Miquel Beltran on 24.04.16.
 * More on http://beltranfebrer.com
 */
@Module(includes = {ContextModule.class})
public class PicassoModule {
    @Provides
    @Singleton
    public Picasso providesPicasso(Context context) {
        return Picasso.with(context);
    }
}
