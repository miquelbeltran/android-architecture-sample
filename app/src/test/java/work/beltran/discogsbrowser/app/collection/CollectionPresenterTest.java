package work.beltran.discogsbrowser.app.collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class CollectionPresenterTest {
    CollectionPresenter presenter;
    UserProfile userProfile;
    UserCollection userCollection;
    List<Record> recordList;

    @Mock
    private CollectionInteractor interactor;
    @Mock
    private ProfileInteractor profileInteractor;
    @Mock
    private ICollectionView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CollectionPresenter(interactor, profileInteractor);
        userProfile = new UserProfile();
        userCollection = new UserCollection();
        recordList = new ArrayList<>();
        userCollection.setRecords(recordList);
        when(interactor.getCollection(anyInt())).thenReturn(Observable.just(userCollection));
        when(profileInteractor.getProfile()).thenReturn(Observable.just(userProfile));
    }

    @Test
    public void attachView() throws Exception {
        presenter.attachView(view);
        verify(view).display(userProfile);
        verify(view).addRecords(recordList);
    }

    @Test
    public void loadMore() throws Exception {
        userCollection.setPagination(new Pagination());
        userCollection.getPagination().setPage(0);
        userCollection.getPagination().setPages(2);
        presenter.attachView(view);
        presenter.loadMore();
        verify(interactor).getCollection(1);
    }
}