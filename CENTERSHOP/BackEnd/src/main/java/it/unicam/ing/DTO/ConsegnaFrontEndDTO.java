package it.unicam.ing.DTO;

import java.time.LocalTime;

public class ConsegnaFrontEndDTO {

	private LocalTime ora;
	private String codiceritiro;
	
	public ConsegnaFrontEndDTO(LocalTime ora, String codiceritiro) {
		this.ora = ora;
		this.codiceritiro = codiceritiro;
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public String getCodiceritiro() {
		return codiceritiro;
	}

	public void setCodiceritiro(String codiceritiro) {
		this.codiceritiro = codiceritiro;
	}
	
	
}
