package work.beltran.discogsbrowser.business;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface WantedInteractor {
    Observable<UserWanted> getWanted(int page);
}
