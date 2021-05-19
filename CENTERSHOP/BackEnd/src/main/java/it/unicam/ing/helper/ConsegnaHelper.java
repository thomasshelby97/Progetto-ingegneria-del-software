package it.unicam.ing.helper;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.ConsegnaAdminDTO;
import it.unicam.ing.DTO.ConsegnaCorriereDTO;
import it.unicam.ing.DTO.NegozioDTO;
import it.unicam.ing.models.Consegna;
import it.unicam.ing.models.Corriere;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.ProdottoRepository;


@Service
public class ConsegnaHelper {
	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	public ConsegnaCorriereDTO parse(Corriere c, Consegna consegna) {
		
		List<NegozioDTO> negozi = new ArrayList<NegozioDTO>();
		for (Prodotto prod : consegna.getProdotti()) {
				if(!(negozi.contains(new NegozioDTO(prod.getCommerciante().getNegozio(),prod.getCommerciante().getVia()))))
						negozi.add(new NegozioDTO(prod.getCommerciante().getNegozio(),prod.getCommerciante().getVia()));
			
		}
		return new ConsegnaCorriereDTO(negozi,consegna.getPuntoritiro(),consegna.getCodiceritiro(),consegna.getCliente(),consegna.getOra());
	}

	public double findImporto(ConsegnaAdminDTO consegna) {
		double importo =0;
		for (String id : consegna.getProdotti()) {
			Prodotto p = prodottoRepository.findById(id).get();
			importo+= p.getPrezzo();	
			
		}
		return importo;
	}

	public Corriere findCorriereDisp() {
		String username = "";
		LocalTime orario = LocalTime.MAX;
		for (Corriere c : corriereRepository.findAll()) {
			if(c.getOrariodisponibilita().isBefore(orario)) {
				orario = c.getOrariodisponibilita();
				username = c.getUsername();
			}
		}
		return corriereRepository.findByUsername(username);
		
	}

	public int findNumeroNegozi(ConsegnaAdminDTO consegnaDTO) {
		List<String> negozi = new ArrayList<String>();
		for (String id : consegnaDTO.getProdotti()) {
			Prodotto p = prodottoRepository.findById(id).get();
			if(!(negozi.contains(p.getCommerciante().getNegozio())))
				negozi.add(p.getCommerciante().getNegozio());
		}
		return negozi.size();
	}

	
	
}
