package it.unicam.ing.DTO;

import java.time.LocalTime;
import java.util.List;



public class CorriereDTO {
	private String username; 
	private LocalTime orariodisponibilita;
	private String azienda;
	private List<ConsegnaCorriereDTO> consegne;
	
	public CorriereDTO(String username, LocalTime orariodisponibilita, String azienda,
			List<ConsegnaCorriereDTO> consegne) {
		super();
		this.username = username;
		this.orariodisponibilita = orariodisponibilita;
		this.azienda = azienda;
		this.consegne = consegne;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalTime getOrariodisponibilita() {
		return orariodisponibilita;
	}

	public void setOrariodisponibilita(LocalTime orariodisponibilita) {
		this.orariodisponibilita = orariodisponibilita;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public List<ConsegnaCorriereDTO> getConsegne() {
		return consegne;
	}

	public void setConsegne(List<ConsegnaCorriereDTO> consegne) {
		this.consegne = consegne;
	}
	
	
	
	
}
