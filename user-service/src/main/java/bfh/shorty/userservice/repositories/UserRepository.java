package bfh.shorty.userservice.repositories;

import bfh.shorty.userservice.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, String> {

}
