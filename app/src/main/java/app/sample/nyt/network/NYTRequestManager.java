package app.sample.nyt.network;

public class NYTRequestManager {

    private static NYTRequestManager thisInstance;

    public static NYTRequestManager getInstance() {
        if (thisInstance == null) thisInstance = new NYTRequestManager();
        return thisInstance;
    }

    private NYTRequestManager() {
    }

    public void start() {

    }

}
