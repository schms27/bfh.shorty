package bfh.shorty.shortlinkservice.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Domain {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
