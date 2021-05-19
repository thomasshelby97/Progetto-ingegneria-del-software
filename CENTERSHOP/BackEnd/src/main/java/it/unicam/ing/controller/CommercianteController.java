package it.unicam.ing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ing.DTO.DisponibilitaDTO;
import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.DTO.ProdottoScontatoDTO;
import it.unicam.ing.helper.JwtUtil;
import it.unicam.ing.models.Commerciante;
import it.unicam.ing.serviceInterface.ICommercianteService;


@RestController
public class CommercianteController {

	@Autowired
	private ICommercianteService commercianteServiceImpl;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	
	@RequestMapping(value = "/commerciante/data", method = RequestMethod.GET)
	public Commerciante getCommerciante(@RequestHeader("Authorization") String authorization){
		
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return commercianteServiceImpl.getCommerciante(username);
	}
	
	@RequestMapping(value = "/commerciante/addNewProdotto", method = RequestMethod.POST)
	public void addNewProdotto(@RequestHeader("Authorization") String authorization, @RequestBody ProdottoDTO prodotto){

		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		commercianteServiceImpl.addNewProdotto(username, prodotto);
	}
	
	@RequestMapping(value = "/commerciante/prodotti", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdotti(@RequestHeader("Authorization") String authorization){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return commercianteServiceImpl.getProdotti(username);
	}
	
	@DeleteMapping(value = "/commerciante/deleteProdotto")
	public void deleteProdotto(@RequestHeader("Authorization") String authorization, @RequestBody String id){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
        String idd = id.substring(10,40);  
		commercianteServiceImpl.deleteProdotto(username, idd);
	}
	
	@RequestMapping(value = "/commerciante/addPromozione", method = RequestMethod.POST)
	public void addPromozione(@RequestHeader("Authorization") String authorization, @RequestBody ProdottoScontatoDTO prodotto){
		System.out.println(prodotto.getSconto());
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		commercianteServiceImpl.addPromozione(username, prodotto);
	}
	
	@PutMapping(value = "/commerciante/rimuoviPromozione")
	public void rimuoviPromozione(@RequestHeader("Authorization") String authorization, @RequestBody String id){

		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
        String idd = id.substring(10,40);  
		commercianteServiceImpl.rimuoviPromozione(username, idd);
	}
	
	@RequestMapping(value = "/commerciante/prodottiScontati", method = RequestMethod.GET)
	public List<ProdottoDTO> getProdottiScontati(@RequestHeader("Authorization") String authorization){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return commercianteServiceImpl.getProdottiScontati(username);
	}
	
	@PutMapping(value = "/commerciante/cambiaDisponibilita")
	public void cambiaDisponibilita(@RequestHeader("Authorization") String authorization, @RequestBody DisponibilitaDTO disp){

		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		commercianteServiceImpl.cambiaDisponibilita(username, disp);
	}
	
	@RequestMapping(value = "/commerciante/clienti", method = RequestMethod.GET)
	public int getNumberClienti(){
	
		return commercianteServiceImpl.getNumberClienti();
	}
	
	
	
	
	
}
