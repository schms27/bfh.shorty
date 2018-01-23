package bfh.shorty.shortlinkservice.repositories;

import bfh.shorty.shortlinkservice.entities.ShortLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShortLinkRepository extends CrudRepository<ShortLink, String> {

}
