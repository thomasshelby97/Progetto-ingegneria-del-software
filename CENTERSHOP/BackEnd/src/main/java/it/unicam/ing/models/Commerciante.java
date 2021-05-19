package it.unicam.ing.models;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMERCIANTE")
public class Commerciante {

	@Id
	private String username; 
	
	@Column(nullable = false)
	private String negozio;
	
	
	@Column(nullable = false)
	private String via;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public Commerciante() {
		
	}
	
	public Commerciante(String username, String negozio, String via) {
		super();
		this.username = username;
		this.negozio = negozio;
		this.via = via;
		
	}


	public String getNegozio() {
		return negozio;
	}
	
	public void setNegozio(String negozio) {
		this.negozio = negozio;
	}
	
	
	
	
}
