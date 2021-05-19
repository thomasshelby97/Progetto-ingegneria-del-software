package it.unicam.ing.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PUNTIRITIRO")
public class Puntoritiro {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String via;
	@Column(nullable = false)
	private String posizione;
	
	
	public void setVia(String via) {
		this.via = via;
	}
	public String getVia() {
		return via;
	}
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	public String getPosizione() {
		return posizione;
	}
	
}
