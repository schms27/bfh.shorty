package bfh.shorty.usershortlinkservice.client;

import bfh.shorty.usershortlinkservice.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service"/*, fallback = UserClientFallback.class*/)
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    Resource<User> getUser(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    Resources<User> getUsers();
}
