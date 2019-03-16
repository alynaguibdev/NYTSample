package app.sample.nyt.network.most_recent_articles.response;

import java.io.Serializable;
import java.util.List;

public class MostRecentArticlesResponse implements Serializable {

    private String status;
    private String copyright;
    private String numResults;
    private List<Result> results = null;
    private final static long serialVersionUID = 7391641082393878589L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getNumResults() {
        return numResults;
    }

    public void setNumResults(String numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
