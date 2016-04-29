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
@Module
public class PicassoModule {
    private Context context;

    public PicassoModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Picasso providesPicasso() {
        return Picasso.with(context);
    }
}
