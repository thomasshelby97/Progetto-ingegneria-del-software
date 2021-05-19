package it.unicam.ing.models;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODOTTI")
public class Prodotto {
	@Id
	private String id;
	
	@Column(nullable = false)
	private double prezzo;
	
	@Column(nullable = false)
	private String descrizione;
	
	@ManyToOne
	@JoinColumn(name="categoria", insertable = true, updatable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="commerciante", insertable = true, updatable = false)
	private Commerciante commerciante;
	
	
	@ManyToOne
	@JoinColumn(name="promozione", insertable = true, updatable = true)
	private Promozioni promozione;
	
	@Column(nullable = false)
	private int disponibilita;
	
	
	private String img;
	
	
	

	public Prodotto() {
		
	}
	
	public Prodotto(double prezzo, String descrizione, Categoria categoria, Commerciante commerciante, Promozioni promozione, int disponibilita, String img) {
		super();
		this.id = genereteRandomString(30);
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.commerciante = commerciante;
		this.promozione = promozione;
		this.disponibilita = disponibilita;
		this.img = img;
	}
	
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Commerciante getCommerciante() {
		return commerciante;
	}

	public void setCommerciante(Commerciante commerciante) {
		this.commerciante = commerciante;
	}

	public double getPrezzo() {
		return prezzo-((promozione.getPercsconto()*prezzo)/100);
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	
	public Promozioni getPromozione() {
		return promozione;
	}

	public void setPromozione(Promozioni promozione) {
		this.promozione = promozione;
	}

	private String genereteRandomString(int m) {
		if(m<8) m=8;
		final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final String lower = upper.toLowerCase(Locale.ROOT);
	    final String digits = "0123456789";
	    final String alphanum = upper + lower + digits;
	    
	    String random = "";
	    for(int i =0 ; i<m ;i++) {
	    	double num = Math.random()*(alphanum.length());
	    	int num1 = (int)num;
	    	random += alphanum.charAt(num1);
	    }

		return random;
	}
	
}
