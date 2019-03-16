package app.sample.nyt.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import app.sample.nyt.base.presenter.MyPresenter;
import app.sample.nyt.base.view.MyView;

public class MyActivity<P extends MyPresenter> extends AppCompatActivity implements MyView {

    P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Please wait", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void handleError() {

    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}
