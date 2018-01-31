package bfh.shorty.usershortlinkservice.client;

import bfh.shorty.usershortlinkservice.model.Domain;
import bfh.shorty.usershortlinkservice.model.ShortLink;
import org.springframework.hateoas.Resources;

import java.util.ArrayList;
import java.util.List;

public class ShortLinkFallback implements ShortLinkClient {
    @Override
    public Resources<ShortLink> getShortLinks() {
        Domain domain = new Domain();
        domain.setUrl("--");
        ShortLink shortLink = new ShortLink();
        shortLink.setDomain(domain);
        shortLink.setId("--");
        shortLink.setTargets(new ArrayList<>());

        List<ShortLink> shortLinks = new ArrayList<ShortLink>();
        shortLinks.add(shortLink);

        return new Resources<>(shortLinks);
    }

    @Override
    public Resources<ShortLink>  getShortLinksForUser(String ownerId) {
        return this.getShortLinks();
    }
}
