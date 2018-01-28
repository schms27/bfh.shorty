package bfh.shorty.usershortlinkservice.controller;

import bfh.shorty.usershortlinkservice.client.ShortLinkClient;
import bfh.shorty.usershortlinkservice.client.UserClient;
import bfh.shorty.usershortlinkservice.model.User;
import bfh.shorty.usershortlinkservice.model.UserShortLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserShortlinkController implements ResourceProcessor<RepositoryLinksResource> {

    private static final Logger LOG = LoggerFactory.getLogger(UserShortlinkController.class);

    private final ShortLinkClient shortLinkClient;

    private final UserClient userClient;

    @Autowired
    public UserShortlinkController(ShortLinkClient shortLinkClient, UserClient userClient) {
        this.shortLinkClient = shortLinkClient;
        this.userClient = userClient;
    }

    @RequestMapping(value = "/userShortLink/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resource<UserShortLink> getUserShortlink(@PathVariable("id") String id) {
        User user = userClient.getUser(id).getContent();
        UserShortLink userShortLink = new UserShortLink();
        userShortLink.setUsername(user.getUsername());
        userShortLink.setEmail(user.getEmail());
        userShortLink.setShortLinks(shortLinkClient.getShortLinksForUser(user.getId()).getContent());
        LOG.info("----------- Orchestrating a User and a ShortLink -----------");
        return new Resource<>(userShortLink);
    }

    @RequestMapping(value = "/userShortLink", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resources<UserShortLink> listPersonAddresses() {
        List<UserShortLink> userShortlinkList = new ArrayList<>();
        Resources<User> userList = userClient.getUsers();
        for (User user: userList) {
            UserShortLink userShortLink = new UserShortLink();
            userShortLink.add(linkTo(methodOn(UserShortlinkController.class).getUserShortlink(user.getId())).withSelfRel());
            userShortLink.add(linkTo(methodOn(UserShortlinkController.class).getUserShortlink(user.getId())).withRel("userShortLink"));
            userShortLink.setUsername(user.getUsername());
            userShortLink.setEmail(user.getEmail());
            userShortLink.setShortLinks(shortLinkClient.getShortLinksForUser(user.getId()).getContent());
            userShortlinkList.add(userShortLink);
        }
        LOG.info("----------- Orchestrating multiple Users and Shortlinks -----------");
        return new Resources<>(userShortlinkList);
    }


    @Override
    public RepositoryLinksResource process(RepositoryLinksResource repositoryLinksResource) {
        repositoryLinksResource.add(linkTo(methodOn(UserShortlinkController.class).listPersonAddresses()).withRel("userShortLink"));
        return repositoryLinksResource;
    }
}
