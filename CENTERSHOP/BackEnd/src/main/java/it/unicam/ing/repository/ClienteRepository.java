package it.unicam.ing.repository;

import org.springframework.data.repository.CrudRepository;

import it.unicam.ing.models.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, String> {
	void deleteById(String id);
}
