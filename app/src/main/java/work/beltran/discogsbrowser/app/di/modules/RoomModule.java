package work.beltran.discogsbrowser.app.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import work.beltran.discogsbrowser.business.ReactiveStore;
import work.beltran.discogsbrowser.business.model.Record;
import work.beltran.discogsbrowser.room.DiscoDatabase;
import work.beltran.discogsbrowser.room.RecordToRecordRoomMapper;
import work.beltran.discogsbrowser.room.RoomReactiveStore;

@Module(
        includes = {ContextModule.class}
)
public class RoomModule {
    @Provides
    @Singleton
    public DiscoDatabase provideDiscoDatabase(Context context) {
       return DiscoDatabase.Companion.getInstance(context);
    }

    @Provides
    @Reusable
    public ReactiveStore<Integer, Record> provideRoomReactiveStore(DiscoDatabase database) {
        return new RoomReactiveStore(database, new RecordToRecordRoomMapper());
    }

}
