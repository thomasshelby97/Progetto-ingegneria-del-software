package it.unicam.ing.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Corriere")
public class Corriere {
	@Id
	private String username; 
	@Column(nullable = false)
	private LocalTime orariodisponibilita;
	@Column(nullable = false)
	private String azienda;
	@OneToMany(cascade=CascadeType.ALL)
	@Column(nullable = false)
	private List<Consegna> consegne;
	
	public Corriere() {
		
	}
	public Corriere(String username, LocalTime orariodisponibilita, String azienda) {
		this.username = username;
		this.orariodisponibilita = orariodisponibilita;
		this.azienda = azienda;
		this.consegne = new ArrayList<Consegna>();
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
	public void setConsegne(List<Consegna> consegne) {
		this.consegne = consegne;
	}
	public List<Consegna> getConsegne(){
		return consegne;
	}
	
	
	public void addConsegna(Consegna consegna) {
		consegne.add(consegna);
	}
	
	
	public String getAzienda() {
		return azienda;
	}
	
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	public void deleteConsegna(String codiceritiro) {
		int index = 0;
		for (Consegna consegna : consegne) {
			if(consegna.getCodiceritiro().equals(codiceritiro))
				index = consegne.indexOf(consegna);
		}
		consegne.remove(index);
		
	}
}
