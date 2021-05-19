package it.unicam.ing.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.serviceInterface.IClienteService;


@RestController
public class ClienteController {

	
	
	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value = "/user/prodotti", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdotti(){
		return clienteService.getProdotti();
	}
	
	@RequestMapping(value = "/user/prodottiScontati", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdottiScontati(){
		return clienteService.getProdottiScontati();
	}
	
	
	@RequestMapping(value = "/user/negozio", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdottiCommerciante(@RequestBody String commerciante){
		String negozio = commerciante.substring(17,commerciante.length()-4);
		return clienteService.getProdottiByCommerciante(negozio);
	}
	
	@RequestMapping(value = "/user/categoria", method = RequestMethod.POST)
	public List<ProdottoDTO> getProdottiCategoria(@RequestBody String categoria){
		String cat = categoria.substring(14,categoria.length()-2);
		return clienteService.getProdottiByCategoria(cat);
	}
	
	@RequestMapping(value = "/user/carrello", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdottiCarrello(@RequestBody String categoria){
		String cat = categoria.substring(19,categoria.length()-4);
		return clienteService.getProdottiByCategoria(cat);
	}
	
	@RequestMapping(value = "/user/categorie", method = RequestMethod.GET)
	public List<String> getCategorie(){
		return clienteService.getCategorie();
	}
	
	
	
}
