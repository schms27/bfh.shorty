package bfh.shorty.usershortlinkservice.client;

import bfh.shorty.usershortlinkservice.model.ShortLink;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "short-link-service"/*, fallback = ShortLinkClientFallback.class*/)
public interface ShortLinkClient {
    @RequestMapping(method = RequestMethod.GET, value = "/shortLinks")
    Resources<ShortLink> getShortLinks();

    @RequestMapping(method = RequestMethod.GET, value = "/shortLinks/search/user")
    Resources<ShortLink> getShortLinksForUser(@RequestParam("ownerId") String ownerId);
}
