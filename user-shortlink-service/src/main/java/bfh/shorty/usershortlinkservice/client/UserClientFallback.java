package bfh.shorty.usershortlinkservice.client;

import bfh.shorty.usershortlinkservice.model.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserClientFallback implements UserClient{
    @Override
    public Resource<User> getUser(String id) {
        return new Resource<>(createDummyUser(id));
    }

    @Override
    public Resources<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(createDummyUser("0"));
        return null;
    }

    private User createDummyUser(String id) {
        User user = new User();
        user.setEmail("no@e-mail.address");
        user.setId(id);
        user.setUsername("no name");

        return user;
    }
}
