package org.sid.Cinema.dao;


import org.sid.Cinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

}
