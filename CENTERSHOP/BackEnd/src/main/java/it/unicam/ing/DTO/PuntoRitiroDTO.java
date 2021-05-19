package it.unicam.ing.DTO;

public class PuntoRitiroDTO {

	public String via;
	public String posizione;
	
	
	public PuntoRitiroDTO(String via, String posizione) {
		super();
		this.via = via;
		this.posizione = posizione;
	}
	
	
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}


	public String getPosizione() {
		return posizione;
	}


	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	
	
	
}
