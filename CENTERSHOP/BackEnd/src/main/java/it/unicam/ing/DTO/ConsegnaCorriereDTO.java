package it.unicam.ing.DTO;

import java.time.LocalTime;
import java.util.List;

public class ConsegnaCorriereDTO {

	private List<NegozioDTO> negozi;
	private String viaPuntoRitiro;
	private String codiceRitiro;
	private String nominativoCliente;
	private LocalTime orario;
	
		
	
	public ConsegnaCorriereDTO(List<NegozioDTO> negozi, String viaPuntoRitiro, String codiceRitiro,
			String nominativoCliente, LocalTime orario) {
		super();
		this.negozi = negozi;
		this.viaPuntoRitiro = viaPuntoRitiro;
		this.codiceRitiro = codiceRitiro;
		this.nominativoCliente = nominativoCliente;
		this.orario = orario;
	}
	
	
	
	
	public List<NegozioDTO> getNegozi() {
		return negozi;
	}


	public void setNegozi(List<NegozioDTO> negozi) {
		this.negozi = negozi;
	}


	public String getViaPuntoRitiro() {
		return viaPuntoRitiro;
	}
	public void setViaPuntoRitiro(String viaPuntoRitiro) {
		this.viaPuntoRitiro = viaPuntoRitiro;
	}
	public String getCodiceRitiro() {
		return codiceRitiro;
	}
	public void setCodiceRitiro(String codiceRitiro) {
		this.codiceRitiro = codiceRitiro;
	}
	public String getNominativoCliente() {
		return nominativoCliente;
	}
	public void setNominativoCliente(String nominativoCliente) {
		this.nominativoCliente = nominativoCliente;
	}

	public LocalTime getOrario() {
		return orario;
	}

	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	
	

}
