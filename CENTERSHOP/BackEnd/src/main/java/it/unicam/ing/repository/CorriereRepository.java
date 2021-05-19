package it.unicam.ing.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unicam.ing.models.Corriere;

@Repository
public interface CorriereRepository extends CrudRepository<Corriere, String> {

	Corriere findByUsername(String username);
}
