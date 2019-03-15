package app.sample.nyt.base.presenter;

import java.lang.ref.WeakReference;

import app.sample.nyt.base.view.MyView;

public abstract class MyPresenter<V extends MyView> {

    private WeakReference<V> viewWeakReference;

    public MyPresenter() {
    }

    private WeakReference<V> getFWeakReference() {
        return viewWeakReference;
    }

    protected V getView() {
        if (isViewAttached()) {
            return getFWeakReference().get();
        }
        return null;
    }

    public void attachView(V passedView) {
        setViewWeakReference(new WeakReference<>(passedView));
    }

    public void detachView() {
        if (getFWeakReference() != null) {
            getFWeakReference().clear();
            setViewWeakReference(null);
        }
    }

    protected boolean isViewAttached() {
        return getFWeakReference() != null && getFWeakReference().get() != null;
    }

    private void setViewWeakReference(WeakReference<V> viewWeakReference) {
        this.viewWeakReference = viewWeakReference;
    }
}
