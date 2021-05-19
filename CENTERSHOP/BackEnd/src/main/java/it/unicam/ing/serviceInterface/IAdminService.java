package it.unicam.ing.serviceInterface;

import java.time.LocalTime;

import it.unicam.ing.DTO.ConsegnaAdminDTO;
import it.unicam.ing.DTO.ConsegnaFrontEndDTO;
import it.unicam.ing.DTO.PagamentoDTO;
import it.unicam.ing.DTO.PuntoRitiroDTO;
import it.unicam.ing.DTO.RegisterCommercianteDTO;
import it.unicam.ing.DTO.RegisterCorriereDTO;

public interface IAdminService {

	public ConsegnaFrontEndDTO newConsegna(ConsegnaAdminDTO consegnaDTO);
	public boolean checkIfIsInCenter(PuntoRitiroDTO puntoRitiro);
	public boolean checkPayment(PagamentoDTO payment);
	public void newCliente(String id);
	public void updateTimeLeft(String id);
	public void timePassed();
	public void newCommerciante(RegisterCommercianteDTO login) ;
	public void newCorriere(RegisterCorriereDTO login);
	
}
