package work.beltran.discogsbrowser.api.model.mocks;

import work.beltran.discogsbrowser.api.model.UserProfile;

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
        return UserProfile.builder()
                .setUsername(USERNAME)
                .setAvatarUrl(AVATAR)
                .setNumCollection(NUM_COLLECTION)
                .setNumWantlist(NUM_WANTLIST)
                .build();
    }
}
