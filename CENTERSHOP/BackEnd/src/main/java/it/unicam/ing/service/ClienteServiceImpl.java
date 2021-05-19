package it.unicam.ing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.helper.ProdottoParser;
import it.unicam.ing.models.Categoria;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.repository.CategoriaRepository;
import it.unicam.ing.repository.ProdottoRepository;
import it.unicam.ing.serviceInterface.IClienteService;


@Service
public class ClienteServiceImpl implements IClienteService{

	
	@Autowired
	private ProdottoRepository prodottoRepository;
	@Autowired
	private ProdottoParser prodottoParser;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List<ProdottoDTO> getProdotti() {
		List<ProdottoDTO> list = new ArrayList<ProdottoDTO>();
		for (Prodotto prod : prodottoRepository.findAll()) {
			list.add(prodottoParser.parse(prod));
		} 
		 return list;
	}


	public List<ProdottoDTO> getProdottiScontati() {
		List<ProdottoDTO> list = new ArrayList<ProdottoDTO>();
		for (Prodotto prod : prodottoRepository.findAll()) {
			if(prod.getPromozione().getPercsconto()!=0)
				list.add(prodottoParser.parse(prod));
		} 
		 return list;
	}


	public List<ProdottoDTO> getProdottiByCommerciante(String commerciante) {
		
		List<ProdottoDTO> list = new ArrayList<ProdottoDTO>();
		for (Prodotto prod : prodottoRepository.findAll()) {
			if(prod.getCommerciante().getNegozio().equals(commerciante))
				list.add(prodottoParser.parse(prod));
		} 
		 return list;
	}


	public List<ProdottoDTO> getProdottiByCategoria(String cat) {
		List<ProdottoDTO> list = new ArrayList<ProdottoDTO>();
		for (Prodotto prod : prodottoRepository.findAll()) {
			if(prod.getCategoria().getNome().equals(cat))
				list.add(prodottoParser.parse(prod));
		} 
		 return list;
	}


	
	public List<String> getCategorie() {
		List<String> list = new ArrayList<String>();
		for (Categoria cat : categoriaRepository.findAll()) {
			list.add(cat.getNome());
		}
		return list;
	}
	
	
	
	
	

}
