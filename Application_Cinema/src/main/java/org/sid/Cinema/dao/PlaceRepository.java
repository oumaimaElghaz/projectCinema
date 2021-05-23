package org.sid.Cinema.dao;


import org.sid.Cinema.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Long>{

}
