package app.sample.nyt.recent_articles.presenter;

import app.sample.nyt.base.presenter.MyPresenter;
import app.sample.nyt.network.most_recent_articles.response.MostRecentArticlesResponse;
import app.sample.nyt.network.most_recent_articles.service.MostRecentArticlesService;
import app.sample.nyt.recent_articles.activity.ArticlesListActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RecentArticlesPresenter extends MyPresenter<ArticlesListActivity> {

    public void loadRecentArticles(int period) {
        MostRecentArticlesService mostRecentArticlesService = new MostRecentArticlesService();
        Observer observer = new Observer<MostRecentArticlesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (isViewAttached()) {
                    getView().showLoading();
                }
            }

            @Override
            public void onNext(MostRecentArticlesResponse mostRecentArticlesResponse) {
                if (mostRecentArticlesResponse != null) {
                    if (mostRecentArticlesResponse.getResults() != null) {
                        if(isViewAttached()){
                            getView().setupRecyclerView(mostRecentArticlesResponse.getResults());
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        mostRecentArticlesService.getMostRecentArticles(period, observer);
    }
}
