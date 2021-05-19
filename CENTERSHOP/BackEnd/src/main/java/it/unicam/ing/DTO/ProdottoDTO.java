package it.unicam.ing.DTO;

public class ProdottoDTO {

	private String id;
	private double prezzo;
	private String descrizione;
	private String categoria;
	private int disponibilita;
	private int percsconto;
	private String img;
	
	

	public ProdottoDTO() {
		
	}

	public ProdottoDTO(String id,double prezzo, String descrizione, String categoria, int disponibilita, int sconto, String img) {
		super();
		this.id=id;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.disponibilita =disponibilita;
		this.percsconto = sconto;
		this.img = img;
	}
	
	
	public ProdottoDTO(double prezzo, String descrizione, String categoria, int disponibilita, String img) {
		this("",prezzo,descrizione,categoria,disponibilita,0, img);
	}
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}

	public int getPercsconto() {
		return percsconto;
	}

	public void setPercsconto(int percsconto) {
		this.percsconto = percsconto;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
