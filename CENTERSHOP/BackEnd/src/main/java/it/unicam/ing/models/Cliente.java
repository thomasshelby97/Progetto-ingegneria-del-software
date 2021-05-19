package it.unicam.ing.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	private String id;
	
	private int timeleft;
	
	
	
	public Cliente() {
		
	}

	public Cliente(String id, int timeleft) {
		super();
		this.id = id;
		this.timeleft = timeleft;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTimeleft() {
		return timeleft;
	}

	public void setTimeleft(int timeleft) {
		this.timeleft = timeleft;
	}

	

	
	
	
	
}
