package it.unicam.ing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unicam.ing.models.Consegna;


@Repository
public interface ConsegnaRepository extends CrudRepository<Consegna, Integer> {

	void deleteByCodiceritiro(String codiceritiro);
	Consegna findByCodiceritiro(String codiceritiro);
}
