package app.sample.nyt.network.most_recent_articles.request;

import app.sample.nyt.base.network.request.ErrorModel;
import app.sample.nyt.base.network.request.MyRequest;
import app.sample.nyt.network.NetworkConstants;
import app.sample.nyt.network.most_recent_articles.response.MostRecentArticlesResponse;

public class MostRecentArticlesRequest extends MyRequest<MostRecentArticlesResponse, ErrorModel> {

    public MostRecentArticlesRequest(int period) {
        baseURL = NetworkConstants.BASE_URL;
        path = NetworkConstants.MOST_RECENT_URL.replace(NetworkConstants.KEY_PERIOD, period + "");
        path += "?" + NetworkConstants.KEY_API_KEY + "=" + NetworkConstants.API_KEY;
    }
}
