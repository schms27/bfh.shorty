package bfh.shorty.usershortlinkservice.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

public class UserShortLink extends ResourceSupport {

    private String username;

    private String email;

    private Collection<ShortLink> shortLinks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<ShortLink> getShortLinks() {
        return shortLinks;
    }

    public void setShortLinks(Collection<ShortLink> shortLinks) {
        this.shortLinks = shortLinks;
    }
}
