package it.unicam.ing.DTO;

public class NegozioDTO {

	private String nomeNegozio;
	private String viaNegozio;
	
	
	public NegozioDTO(String nomeNegozio, String viaNegozio) {
		super();
		this.nomeNegozio = nomeNegozio;
		this.viaNegozio = viaNegozio;
	}


	public String getNomeNegozio() {
		return nomeNegozio;
	}


	public void setNomeNegozio(String nomeNegozio) {
		this.nomeNegozio = nomeNegozio;
	}


	public String getViaNegozio() {
		return viaNegozio;
	}


	public void setViaNegozio(String viaNegozio) {
		this.viaNegozio = viaNegozio;
	}
	
	public boolean myContain(String nomeNegozio) {
		System.out.println(nomeNegozio);
		System.out.println(this.nomeNegozio);
		return (this.nomeNegozio.equals(nomeNegozio));
	}
	
}
