package work.beltran.discogsbrowser.api.di.modules;

import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.UserCollection;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
public class UserCollectionMockModule extends UserCollectionModule {
    private UserCollection userCollection;

    public UserCollectionMockModule(UserCollection userCollection) {
        super(null, null, null);
        this.userCollection = userCollection;
    }

    @Override
    public UserCollection provideUserCollection(DiscogsService service) {
        return  userCollection;
    }
}
