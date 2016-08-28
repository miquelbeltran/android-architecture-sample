package work.beltran.discogsbrowser.app.base;

import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class BasePresenter<V extends BaseView> {
    @Nullable
    private V view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
        unsubscribeAll();
    }

    public void unsubscribeAll() {
        compositeSubscription.clear();
    }

    @Nullable
    public V getView() {
        return view;
    }
}
