package it.unicam.ing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.unicam.ing.models.Commerciante;
import it.unicam.ing.models.Prodotto;

public interface ProdottoRepository extends  CrudRepository<Prodotto, String> {

	List<Prodotto> findProdottiByCommerciante(Commerciante c);
	void deleteById(String id);
	Optional<Prodotto> findById(String id);
}
