package it.unicam.ing.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIE")
public class Categoria {

	
	@Id
	private String nome;
	
	
	public Categoria() {
		
	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
