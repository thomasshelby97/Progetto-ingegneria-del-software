package it.unicam.ing.DTO;

public class RegisterCorriereDTO {

	private String username;
	private String password;
	private String azienda;
	
	
	public RegisterCorriereDTO(String username, String password, String azienda) {
		super();
		this.username = username;
		this.password = password;
		this.azienda = azienda;
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


	public String getAzienda() {
		return azienda;
	}


	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	
	
	
	
	
}
