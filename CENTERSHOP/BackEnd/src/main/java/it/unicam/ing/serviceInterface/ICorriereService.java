package it.unicam.ing.serviceInterface;

import java.time.LocalTime;
import java.util.List;

import it.unicam.ing.DTO.ConsegnaCorriereDTO;
import it.unicam.ing.DTO.CorriereDTO;
import it.unicam.ing.models.Puntoritiro;

public interface ICorriereService {
	public List<ConsegnaCorriereDTO> getConsegne(String username);
	public CorriereDTO getCorriere(String username);
	public void endConsegna(String username, String codiceritiro, LocalTime orario);
	public List<Puntoritiro> getPuntiRitiro(String username);
	public ConsegnaCorriereDTO getConsegnaDetails(String username, String codice);
}
