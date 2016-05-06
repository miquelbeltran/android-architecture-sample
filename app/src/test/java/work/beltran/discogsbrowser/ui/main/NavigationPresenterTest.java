package work.beltran.discogsbrowser.ui.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class NavigationPresenterTest {
    NavigationPresenter presenter;

    @Mock
    NavigationView view;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new NavigationPresenter();
        presenter.setView(view);
    }

    @Test
    public void testSelectCollection() throws Exception {
        presenter.tabItem(0, false);
        verify(view).showFragment(NavigationView.FragmentTag.Collection, true);
    }

    @Test
    public void testSelectCollectionAgain() throws Exception {
        presenter.tabItem(0, true);
        verify(view, never()).showFragment(NavigationView.FragmentTag.Collection, true);
    }

    @Test
    public void testSelectWant() throws Exception {
        presenter.tabItem(1, false);
        verify(view).showFragment(NavigationView.FragmentTag.Wantlist, true);
    }

    @Test
    public void testSelectWantAgain() throws Exception {
        presenter.tabItem(1, true);
        verify(view, never()).showFragment(NavigationView.FragmentTag.Wantlist, true);
    }

    @Test
    public void testSelectSearch() throws Exception {
        presenter.tabItem(2, false);
        verify(view).showFragment(NavigationView.FragmentTag.Search, true);
    }

    @Test
    public void testSelectSearchAgain() throws Exception {
        presenter.tabItem(2, true);
        verify(view, never()).showFragment(NavigationView.FragmentTag.Search, true);
    }
}
