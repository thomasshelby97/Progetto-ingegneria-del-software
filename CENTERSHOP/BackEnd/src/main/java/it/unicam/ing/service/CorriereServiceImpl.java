package it.unicam.ing.service;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.ConsegnaCorriereDTO;
import it.unicam.ing.DTO.CorriereDTO;
import it.unicam.ing.helper.ConsegnaHelper;
import it.unicam.ing.helper.CorriereHelper;
import it.unicam.ing.models.Consegna;
import it.unicam.ing.models.Corriere;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.models.Puntoritiro;
import it.unicam.ing.repository.ConsegnaRepository;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.PuntoritiroRepository;
import it.unicam.ing.serviceInterface.ICorriereService;

@Service
@Transactional
public class CorriereServiceImpl implements ICorriereService {

	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private PuntoritiroRepository puntoritiroRepository;
	
	@Autowired
	private ConsegnaRepository consegnaRepository;
	
	@Autowired
	private ConsegnaHelper consegnaParser;
	
	@Autowired
	private CorriereHelper corriereHelper;
	

	public List<ConsegnaCorriereDTO> getConsegne(String username) {		
		List<Consegna> list = corriereRepository.findByUsername(username).getConsegne();
		List<ConsegnaCorriereDTO> listDTO = new ArrayList<ConsegnaCorriereDTO>();
		for (Consegna consegna : list) {
			listDTO.add(consegnaParser.parse(corriereRepository.findByUsername(username), consegna));
		}
		return listDTO;
	}

	public CorriereDTO getCorriere(String username) {
		return corriereHelper.parseToDTO(corriereRepository.findByUsername(username));
	}



	public void endConsegna(String username, String codiceritiro, LocalTime orario) {
		Corriere c = corriereRepository.findByUsername(username);
		c.deleteConsegna(codiceritiro);
		c.setOrariodisponibilita(orario);
		corriereRepository.save(c);
		Consegna cons = consegnaRepository.findByCodiceritiro(codiceritiro);
		cons.setProdotti(new ArrayList<Prodotto>());
		consegnaRepository.save(cons);
		consegnaRepository.deleteByCodiceritiro(codiceritiro);
	
	}


	public List<Puntoritiro> getPuntiRitiro(String username) {
		List<Puntoritiro> list = new ArrayList<Puntoritiro>();
		for (Puntoritiro puntoritiro : puntoritiroRepository.findAll()) {
			list.add(puntoritiro);
		 } 
		
		return list;
	}



	public ConsegnaCorriereDTO getConsegnaDetails(String username, String codice) {
		ConsegnaCorriereDTO cons = null;
		Corriere c = corriereRepository.findByUsername(username);
		for (Consegna consegna : c.getConsegne()) {
			if(consegna.getCodiceritiro().equals(codice))
				cons = consegnaParser.parse(c,consegna);
		}
		return cons;
	}
	
	
	
}
