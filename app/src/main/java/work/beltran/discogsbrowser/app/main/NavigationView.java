package work.beltran.discogsbrowser.app.main;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public interface NavigationView {
    enum FragmentTag {
        Collection,
        Wantlist,
        Search
    };

    void showFragment(FragmentTag tagCollection, boolean toRight);
}
