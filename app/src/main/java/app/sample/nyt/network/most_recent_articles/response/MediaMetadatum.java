package app.sample.nyt.network.most_recent_articles.response;

import java.io.Serializable;

public class MediaMetadatum implements Serializable {

    private String url;
    private String format;
    private String height;
    private String width;
    private final static long serialVersionUID = 5281974450354571261L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

}
