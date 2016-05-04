package work.beltran.discogsbrowser.ui.errors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.ui.settings.Settings;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ErrorPresenterTest {
    ErrorPresenter errorPresenter;
    Settings settings;
    ErrorHandlingView view;

    @Before
    public void setUp() throws Exception {
        settings = mock(Settings.class);
        view = mock(ErrorHandlingView.class);
        errorPresenter = new ErrorPresenter(settings);
    }

    @Test
    public void testOnErrorWithView() throws Exception {
        errorPresenter.setView(view);
        Throwable throwable = new Throwable("Test");
        errorPresenter.onError(throwable);
        verify(settings).storeApiKey("");
        verify(view).showErrorInfo("Test");
    }

    @Test
    public void testOnErrorWithoutView() throws Exception {
        Throwable throwable = new Throwable("Test");
        errorPresenter.onError(throwable);
        verify(settings).storeApiKey("");
        verify(view, never()).showErrorInfo(anyString());
    }
}
