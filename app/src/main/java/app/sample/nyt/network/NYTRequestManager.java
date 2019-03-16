package app.sample.nyt.network;

import com.google.gson.Gson;

import java.io.IOException;

import app.sample.nyt.base.network.request.ErrorConstants;
import app.sample.nyt.base.network.request.ErrorModel;
import app.sample.nyt.base.network.request.MyRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NYTRequestManager {

    private static NYTRequestManager thisInstance;
    private static OkHttpClient client;

    public static NYTRequestManager getInstance() {
        if (thisInstance == null) thisInstance = new NYTRequestManager();
        client = new OkHttpClient();
        return thisInstance;
    }

    private NYTRequestManager() {
    }

    public void start(MyRequest<?, ErrorModel> request) {
        ErrorModel errorModel = null;
        try {
            String responseBody = get(request.getBaseURL() + request.getPath());

            if (responseBody != null) {
                request.parseResponse(responseBody);
            } else {
                errorModel = new ErrorModel(ErrorConstants.E500, "No Response Body");
            }

            if (errorModel != null) {
                request.setErrorModel(errorModel);
            }
        } catch (Exception e) {
            errorModel = new ErrorModel(ErrorConstants.E500, e.getMessage());
            request.setErrorModel(errorModel);
            e.printStackTrace();
        }
    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(NetworkConstants.JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response;
        }
    }

}
