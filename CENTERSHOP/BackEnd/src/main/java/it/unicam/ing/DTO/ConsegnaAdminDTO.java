package it.unicam.ing.DTO;


import java.util.List;



public class ConsegnaAdminDTO {

	private List<String> prodotti;
	private String puntoRitiro;
	private String nominativo;
	
	
	public ConsegnaAdminDTO(List<String> prodotti, String puntoRitiro, String nominativo) {
		super();
		this.prodotti = prodotti;
		this.puntoRitiro = puntoRitiro;
		this.nominativo = nominativo;
	}


	public List<String> getProdotti() {
		return prodotti;
	}


	public void setProdotti(List<String> prodotti) {
		this.prodotti = prodotti;
	}


	public String getPuntoRitiro() {
		return puntoRitiro;
	}


	public void setPuntoRitiro(String puntoRitiro) {
		this.puntoRitiro = puntoRitiro;
	}


	public String getNominativo() {
		return nominativo;
	}


	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	
	
}
