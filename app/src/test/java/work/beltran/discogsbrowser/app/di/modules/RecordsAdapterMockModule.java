package work.beltran.discogsbrowser.app.di.modules;

import dagger.Module;
import work.beltran.discogsbrowser.business.old.ApiFrontend;
import work.beltran.discogsbrowser.app.main.wantlist.WantRecordsAdapterOld;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@Module
public class RecordsAdapterMockModule extends RecordsAdapterModule {
    private WantRecordsAdapterOld mockWant;


    @Override
    public WantRecordsAdapterOld providesWantRecordsAdapter(ApiFrontend apiFrontend) {
        return mockWant;
    }
}
