package it.unicam.ing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unicam.ing.models.Commerciante;

@Repository
public interface CommercianteRepository extends CrudRepository<Commerciante, String>{

	Commerciante findByUsername(String username);
	void deleteByUsername(String username);
}
