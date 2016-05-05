package work.beltran.discogsbrowser.ui.main;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public class NavigationPresenter {
    NavigationView view;

    public void setView(NavigationView view) {
        this.view = view;
    }

    public void tabItem(int position, boolean wasSelected) {
        switch (position) {
            case 0:
                if (!wasSelected) {
                    view.showFragment(NavigationView.FragmentTag.Collection);
                }
                break;
            case 1:
                if (!wasSelected) {
                    view.showFragment(NavigationView.FragmentTag.Wantlist);
                }
                break;
            case 2:
                if (!wasSelected) {
                    view.showFragment(NavigationView.FragmentTag.Search);
                }
                break;
        }
    }
}
