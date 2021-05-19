package it.unicam.ing.DTO;

import java.time.LocalTime;

public class EndConsegnaDTO {

	private String codiceritiro;
	private LocalTime orario;
	
	
	public EndConsegnaDTO(String codiceritiro, LocalTime orario) {
		super();
		this.codiceritiro = codiceritiro;
		this.orario = orario;
	}


	public String getCodiceritiro() {
		return codiceritiro;
	}


	public void setCodiceritiro(String codiceritiro) {
		this.codiceritiro = codiceritiro;
	}


	public LocalTime getOrario() {
		return orario;
	}


	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	
	
	
}
