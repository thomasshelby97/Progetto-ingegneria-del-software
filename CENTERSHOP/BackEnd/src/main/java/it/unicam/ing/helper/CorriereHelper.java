package it.unicam.ing.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.ConsegnaCorriereDTO;
import it.unicam.ing.DTO.CorriereDTO;
import it.unicam.ing.models.Consegna;
import it.unicam.ing.models.Corriere;
@Service
public class CorriereHelper {

	@Autowired
	private ConsegnaHelper consegnaHelper;
	
	public CorriereDTO parseToDTO(Corriere c) {
		List<ConsegnaCorriereDTO> consegne = new ArrayList<ConsegnaCorriereDTO>();
		for (Consegna cons : c.getConsegne()) {
			consegne.add(consegnaHelper.parse(c,cons));
		}
		return new CorriereDTO(c.getUsername(),c.getOrariodisponibilita(),c.getAzienda(),consegne);
	}

}
