package work.beltran.discogsbrowser.app.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.app.search.SearchPresenter;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.SearchInteractor;
import work.beltran.discogsbrowser.business.search.SearchInteractorImpl;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
@Module(includes = {
        DiscogsModule.class,
        RxJavaSchedulersModule.class
})
public class SearchModule {
    @Provides
    @Singleton
    public SearchInteractor searchInteractor(DiscogsService service,
                                             RxJavaSchedulers schedulers) {
        return new SearchInteractorImpl(service, schedulers);
    }

    @Provides
    @Singleton
    public SearchPresenter searchPresenter(SearchInteractor interactor) {
        return new SearchPresenter(interactor);
    }
}
