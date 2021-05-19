package it.unicam.ing.DTO;

public class ProdottoScontatoDTO {

	private String id;
	private int sconto;
	
	public ProdottoScontatoDTO() {}
	
	public ProdottoScontatoDTO(String id, int sconto) {
		super();
		this.id = id;
		this.sconto = sconto;
	}

	public String getProdotto() {
		return id;
	}

	public void setProdotto(String id) {
		this.id = id;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	
	
	
	
}
