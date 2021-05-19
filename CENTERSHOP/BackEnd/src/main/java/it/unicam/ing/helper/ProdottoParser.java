package it.unicam.ing.helper;

import org.springframework.stereotype.Service;


import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.models.Prodotto;

@Service
public class ProdottoParser {

	public ProdottoDTO parse (Prodotto p) {
		ProdottoDTO prod = new ProdottoDTO(p.getId(),p.getPrezzo(),p.getDescrizione(),p.getCategoria().getNome(),p.getDisponibilita(),p.getPromozione().getPercsconto(),p.getImg());
		return prod;
	}

	
}
