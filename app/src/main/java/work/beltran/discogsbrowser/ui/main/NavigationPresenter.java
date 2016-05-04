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
                view.removeFragment(NavigationView.FragmentTag.Wantlist);
                view.removeFragment(NavigationView.FragmentTag.Search);
                break;
            case 1:
                if (!wasSelected) {
                    view.showFragment(NavigationView.FragmentTag.Wantlist);
                }
                view.removeFragment(NavigationView.FragmentTag.Search);
                view.removeFragment(NavigationView.FragmentTag.Collection);
                break;
            case 2:
                if (!wasSelected) {
                    view.showFragment(NavigationView.FragmentTag.Search);
                }
                view.removeFragment(NavigationView.FragmentTag.Wantlist);
                view.removeFragment(NavigationView.FragmentTag.Collection);
                break;
        }
    }
}
