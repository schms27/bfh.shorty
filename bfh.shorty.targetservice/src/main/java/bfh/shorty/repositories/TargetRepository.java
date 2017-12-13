package bfh.shorty.repositories;

import bfh.shorty.entities.Target;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TargetRepository extends CrudRepository<Target, String> {
}
