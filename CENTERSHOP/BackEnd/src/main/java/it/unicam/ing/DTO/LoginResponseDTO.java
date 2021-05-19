package it.unicam.ing.DTO;

public class LoginResponseDTO {

	String jwt;
	String ruolo;
	
	public LoginResponseDTO(String jwt, String ruolo) {
		super();
		this.jwt = jwt;
		this.ruolo = ruolo;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
}
