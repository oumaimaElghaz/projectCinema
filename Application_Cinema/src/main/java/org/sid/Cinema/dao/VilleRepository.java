package org.sid.Cinema.dao;


import org.sid.Cinema.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long>{

}