package it.unicam.ing.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.DisponibilitaDTO;
import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.DTO.ProdottoScontatoDTO;
import it.unicam.ing.helper.ProdottoParser;
import it.unicam.ing.models.Categoria;
import it.unicam.ing.models.Commerciante;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.models.Promozioni;
import it.unicam.ing.repository.CategoriaRepository;
import it.unicam.ing.repository.ClienteRepository;
import it.unicam.ing.repository.CommercianteRepository;
import it.unicam.ing.repository.ProdottoRepository;
import it.unicam.ing.repository.PromozioneRepository;
import it.unicam.ing.serviceInterface.ICommercianteService;

@Service
public class CommercianteServiceImpl implements ICommercianteService {

	
	@Autowired
	private CommercianteRepository commercianteRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PromozioneRepository promozioneRepository;	
	@Autowired
	private ProdottoRepository prodottoRepository;
	@Autowired
	private ProdottoParser prodottoParser;
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Commerciante getCommerciante(String username) {	
		return commercianteRepository.findByUsername(username);
	}

	public void addNewProdotto(String username, ProdottoDTO prodotto) {
		Commerciante com = commercianteRepository.findByUsername(username);
		Categoria cat = categoriaRepository.findCategoriaByNome(prodotto.getCategoria());
		Promozioni prom = promozioneRepository.findPromozioniByPercsconto(prodotto.getPercsconto());
		prodottoRepository.save(new Prodotto(prodotto.getPrezzo(),prodotto.getDescrizione(),cat,com,prom,prodotto.getDisponibilita(),prodotto.getImg()));
		
	}

	public List<ProdottoDTO> getProdotti(String username) {
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		List<ProdottoDTO> listDTO = new ArrayList<ProdottoDTO>();
		for (Prodotto prodotto : list) {
			listDTO.add(prodottoParser.parse(prodotto));
		}
		return listDTO;
	}

	public void deleteProdotto(String username, String id) {	
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		for (Prodotto prodotto : list) {
			if(prodotto.getId().equals(id))
				prodottoRepository.deleteById(id);
		}	
		
	}

	public void addPromozione(String username, ProdottoScontatoDTO prodotto) {
		Promozioni prom = promozioneRepository.findPromozioniByPercsconto(prodotto.getSconto());
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		for (Prodotto p : list) {
			if(p.getId().equals(prodotto.getId())) {
				p.setPromozione(prom);
				prodottoRepository.save(p);
			}
		}	
	}

	public void rimuoviPromozione(String username, String id) {
		Promozioni prom = promozioneRepository.findPromozioniByPercsconto(0);
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		for (Prodotto p : list) {
			if(p.getId().equals(id)) {
				p.setPromozione(prom);
				prodottoRepository.save(p);
			}
		}
	}

	public List<ProdottoDTO> getProdottiScontati(String username) {
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		List<ProdottoDTO> listDTO = new ArrayList<ProdottoDTO>();
		for (Prodotto prodotto : list) {
			if(prodotto.getPromozione().getPercsconto()!=0)
				listDTO.add(prodottoParser.parse(prodotto));
		}
		return listDTO;
		
	}

	public void cambiaDisponibilita(String username, DisponibilitaDTO disp) {
		Commerciante c = commercianteRepository.findByUsername(username);
		List<Prodotto> list = prodottoRepository.findProdottiByCommerciante(c);
		for (Prodotto prodotto : list) {
			if(prodotto.getId().equals(disp.getId())) {
				prodotto.setDisponibilita(disp.getDisponibilita());
				prodottoRepository.save(prodotto);
			}
		}
	}

	public int getNumberClienti() {
		return (int) clienteRepository.count();
		
	}
	
	
	
	

}
