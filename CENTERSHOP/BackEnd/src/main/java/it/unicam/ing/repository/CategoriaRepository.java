package it.unicam.ing.repository;

import org.springframework.data.repository.CrudRepository;

import it.unicam.ing.models.Categoria;


public interface CategoriaRepository extends CrudRepository<Categoria, String>{

	Categoria findCategoriaByNome(String nome);
}
