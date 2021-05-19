package it.unicam.ing.DTO;

public class DisponibilitaDTO {

	private String id;
	private int disponibilita;
	
	
	public DisponibilitaDTO(String id, int disponibilita) {
		super();
		this.id = id;
		this.disponibilita = disponibilita;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getDisponibilita() {
		return disponibilita;
	}


	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	
	
	
}
