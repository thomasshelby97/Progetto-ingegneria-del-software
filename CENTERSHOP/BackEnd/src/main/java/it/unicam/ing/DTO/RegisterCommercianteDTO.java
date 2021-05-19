package it.unicam.ing.DTO;

public class RegisterCommercianteDTO {

	private String username;
	private String password;
	private String negozio;
	private String via;
	
	
	public RegisterCommercianteDTO(String username, String password, String negozio, String via) {
		super();
		this.username = username;
		this.password = password;
		this.negozio = negozio;
		this.via = via;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNegozio() {
		return negozio;
	}
	public void setNegozio(String negozio) {
		this.negozio = negozio;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	
	
	
	
}
