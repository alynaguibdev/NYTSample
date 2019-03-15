package app.sample.nyt.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.sample.nyt.BuildConfig;
import app.sample.nyt.base.presenter.MyPresenter;
import app.sample.nyt.base.view.MyView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MyFragment<P extends MyPresenter> extends Fragment implements MyView {

    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutRes();

    private Unbinder mUnbinder;

    View mainView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (getLayoutRes() != -1) {
            mainView = inflater.inflate(getLayoutRes(), container, false);
        }
        ButterKnife.setDebug(BuildConfig.DEBUG);
        mUnbinder = ButterKnife.bind(this, mainView);
        presenter.attachView(this);
        return mainView;
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleError() {

    }
}
