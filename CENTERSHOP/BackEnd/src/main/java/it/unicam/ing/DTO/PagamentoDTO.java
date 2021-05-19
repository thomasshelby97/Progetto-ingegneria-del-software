package it.unicam.ing.DTO;

public class PagamentoDTO {

	private String numeroCarta;
	private String codice;
	private String titolareCarta;
	private double importo;
	
	public PagamentoDTO(String numeroCarta, String codice, String titolareCarta, double importo) {
		super();
		this.numeroCarta = numeroCarta;
		this.codice = codice;
		this.titolareCarta = titolareCarta;
		this.importo = importo;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTitolareCarta() {
		return titolareCarta;
	}

	public void setTitolareCarta(String titolareCarta) {
		this.titolareCarta = titolareCarta;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}
	
	
	
	
}
