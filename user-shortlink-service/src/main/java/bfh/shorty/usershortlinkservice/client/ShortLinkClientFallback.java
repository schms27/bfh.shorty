package bfh.shorty.usershortlinkservice.client;

import bfh.shorty.usershortlinkservice.model.Domain;
import bfh.shorty.usershortlinkservice.model.ShortLink;
import bfh.shorty.usershortlinkservice.model.Target;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShortLinkClientFallback implements ShortLinkClient{
    @Override
    public Resources<ShortLink> getShortLinks() {
        return createDummyShortLinks();
    }

    @Override
    public Resources<ShortLink> getShortLinksForUser(String ownerId) {
        return createDummyShortLinks();
    }

    private Resources<ShortLink> createDummyShortLinks() {
        List<Target> targetList = new ArrayList<>();
        Target target = new Target();
        target.setId("-- DUMMY Target --");
        target.setUrl("www.mdurl.com");
        targetList.add(target);

        Domain domain = new Domain();
        domain.setUrl("www.mydummyurl.com");

        List<ShortLink> shortLinkList = new ArrayList<>();
        ShortLink shortLink = new ShortLink();
        shortLink.setId("-- DUMMY ShortLink --");
        shortLink.setDomain(domain);
        shortLink.setTargets(targetList);
        shortLinkList.add(shortLink);

        return new Resources<>(shortLinkList);
    }
}
