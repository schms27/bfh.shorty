package bfh.shorty.targetservice.repositories;

import bfh.shorty.targetservice.entities.ShortLink;
import bfh.shorty.targetservice.entities.Target;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShortLinkRepository extends CrudRepository<ShortLink, String> {

}
