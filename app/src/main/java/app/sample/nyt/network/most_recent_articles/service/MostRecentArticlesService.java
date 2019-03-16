package app.sample.nyt.network.most_recent_articles.service;

import app.sample.nyt.network.NYTRequestManager;
import app.sample.nyt.network.most_recent_articles.request.MostRecentArticlesRequest;
import app.sample.nyt.network.most_recent_articles.response.MostRecentArticlesResponse;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MostRecentArticlesService {

    public Observable<MostRecentArticlesResponse> getMostRecentArticles(int period, Observer<MostRecentArticlesResponse> observer) {
        NYTRequestManager requestManager = NYTRequestManager.getInstance();
        MostRecentArticlesRequest mostRecentArticlesRequest = new MostRecentArticlesRequest(period);
        Observable<MostRecentArticlesResponse> observable = Observable.defer(() -> {
            requestManager.start(mostRecentArticlesRequest);
            return Observable.just(mostRecentArticlesRequest.getResponse());
        });
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
        return observable;
    }

}
