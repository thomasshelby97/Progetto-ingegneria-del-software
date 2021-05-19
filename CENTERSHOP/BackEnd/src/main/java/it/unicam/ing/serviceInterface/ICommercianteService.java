package it.unicam.ing.serviceInterface;

import java.util.List;

import it.unicam.ing.DTO.DisponibilitaDTO;
import it.unicam.ing.DTO.ProdottoDTO;
import it.unicam.ing.DTO.ProdottoScontatoDTO;
import it.unicam.ing.models.Commerciante;

public interface ICommercianteService {
	public Commerciante getCommerciante(String username);
	public void addNewProdotto(String username, ProdottoDTO prodotto);
	public List<ProdottoDTO> getProdotti(String username);
	public void deleteProdotto(String username, String id);
	public void addPromozione(String username, ProdottoScontatoDTO prodotto);
	public void rimuoviPromozione(String username, String id);
	public List<ProdottoDTO> getProdottiScontati(String username);
	public int getNumberClienti();
	public void cambiaDisponibilita(String username, DisponibilitaDTO disp);
}
