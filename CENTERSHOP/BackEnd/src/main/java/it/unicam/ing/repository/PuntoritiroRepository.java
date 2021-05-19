package it.unicam.ing.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import it.unicam.ing.models.Puntoritiro;

public interface PuntoritiroRepository extends CrudRepository<Puntoritiro, Integer> {

	Puntoritiro findByVia (String via);
	List<Puntoritiro> findByPosizione (String posizione);
}
