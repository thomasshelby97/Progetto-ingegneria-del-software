package it.unicam.ing.repository;



import org.springframework.data.repository.CrudRepository;


import it.unicam.ing.models.Promozioni;

public interface PromozioneRepository extends  CrudRepository<Promozioni, Integer> {

	Promozioni findPromozioniByPercsconto(int sconto);
}
