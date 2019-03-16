package app.sample.nyt.base.view;

import app.sample.nyt.base.presenter.MyPresenter;

public interface MyView {

    public void showLoading();

    public void hideLoading();

    public void handleError();

    public MyPresenter getPresenter();
}
