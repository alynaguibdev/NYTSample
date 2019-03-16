package app.sample.nyt.network.most_recent_articles.response;

import java.io.Serializable;
import java.util.List;

public class Medium implements Serializable {

    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private String approvedForSyndication;
    private List<MediaMetadatum> mediaMetadata = null;
    private final static long serialVersionUID = 8692208613129630142L;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(String approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

}
