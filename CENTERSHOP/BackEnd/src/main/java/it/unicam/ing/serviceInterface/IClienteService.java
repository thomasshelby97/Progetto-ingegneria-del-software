package it.unicam.ing.serviceInterface;

import java.util.List;


import it.unicam.ing.DTO.ProdottoDTO;

public interface IClienteService {
	public List<ProdottoDTO> getProdotti();
	public List<ProdottoDTO> getProdottiScontati();
	public List<ProdottoDTO> getProdottiByCommerciante(String commerciante);
	public List<ProdottoDTO> getProdottiByCategoria(String cat);
	public List<String> getCategorie();
	
}
