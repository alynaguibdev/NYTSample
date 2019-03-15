package app.sample.nyt.network.response.most_recent_article;

import java.io.Serializable;

public class MediaMetadatum implements Serializable {

    private String url;
    private String format;
    private Integer height;
    private Integer width;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
