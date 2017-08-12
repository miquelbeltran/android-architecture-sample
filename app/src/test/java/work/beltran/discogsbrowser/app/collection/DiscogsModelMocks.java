package work.beltran.discogsbrowser.app.collection;

import java.util.ArrayList;

import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public class DiscogsModelMocks {

    public static final String USERNAME = "USERNAME";
    public static final String AVATAR = "AVATAR";
    public static final int NUM_COLLECTION = 10;
    public static final int NUM_WANTLIST = 2;

    public static UserProfile getUserProfile() {
        return UserProfile.Companion.builder()
                .setUsername(USERNAME)
                .setAvatarUrl(AVATAR)
                .setNumCollection(NUM_COLLECTION)
                .setNumWantlist(NUM_WANTLIST)
                .build();
    }

    public static UserCollection getUserCollection() {
        return UserCollection.Companion.builder()
                .setRecords(new ArrayList<Record>())
                .setPagination(Pagination.Companion.builder()
                        .page(0)
                        .pages(2)
                        .build())
                .build();
    }
}
