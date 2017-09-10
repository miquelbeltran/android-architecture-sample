package work.beltran.discogsbrowser.app.di.modules;

import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCase;
import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCaseImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.app.collection.CollectionViewModel;
import work.beltran.discogsbrowser.app.common.RecordToRecordItemMapper;
import work.beltran.discogsbrowser.business.CollectionRepository;
import work.beltran.discogsbrowser.business.ReactiveStore;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.collection.CollectionRepositoryImpl;
import work.beltran.discogsbrowser.business.mappers.RecordApiToRecordMapper;
import work.beltran.discogsbrowser.business.model.Record;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@Module(includes = {
        DiscogsModule.class,
        RxJavaSchedulersModule.class,
        ProfileModule.class,
        RoomModule.class,
        UserModule.class
})
public class CollectionModule {

    @Reusable
    @Provides
    public CollectionRepository providesRepository(DiscogsService service,
                                                   RxJavaSchedulers schedulers,
                                                   ReactiveStore<Integer, Record> store,
                                                   @Named("username") String username) {
        return new CollectionRepositoryImpl(service, schedulers, store, new RecordApiToRecordMapper(), username);
    }

    @Reusable
    @Provides
    public RecordCollectionUseCase providesRecordCollectionUseCase(CollectionRepository collectionRepository) {
        return new RecordCollectionUseCaseImpl(collectionRepository);
    }

    @Provides
    public CollectionViewModel provideCollectionViewModel(RecordCollectionUseCase useCase) {
        return new CollectionViewModel(useCase, new RecordToRecordItemMapper());
    }
}
