package it.unicam.ing.controller;


import java.time.LocalTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ing.DTO.ConsegnaCorriereDTO;
import it.unicam.ing.DTO.CorriereDTO;
import it.unicam.ing.DTO.EndConsegnaDTO;
import it.unicam.ing.helper.JwtUtil;
import it.unicam.ing.models.Puntoritiro;
import it.unicam.ing.serviceInterface.ICorriereService;


@RestController
public class CorriereController {

	private String token;
	@Autowired
	private ICorriereService corriereService;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@RequestMapping(value = "corriere/getConsegne", method = RequestMethod.GET)
	public List<ConsegnaCorriereDTO> getConsegne(@RequestHeader("Authorization") String authorization){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return corriereService.getConsegne(username);
	}
	
	@RequestMapping(value = "corriere/consegna", method = RequestMethod.GET)
	public ConsegnaCorriereDTO getConsegnaDetails(@RequestHeader("Authorization") String authorization, @RequestBody String codiceritiro ){
		String codice = codiceritiro.substring(25,75);
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
        System.out.println(codice);
		return corriereService.getConsegnaDetails(username, codice);
	}
	
	
	@RequestMapping(value = "corriere/data", method = RequestMethod.GET)
	public CorriereDTO getCorriere(@RequestHeader("Authorization") String authorization){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return corriereService.getCorriere(username);
	}
	
	/*@RequestMapping(value = "/corriere/addConsegna", method = RequestMethod.POST)
	public void addConsegna(@RequestHeader("Authorization") String authorization, @RequestBody Consegna consegna){

		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		corriereService.addConsegna(username, consegna);
	}*/
	
	@RequestMapping(value = "/corriere/endConsegna",method = RequestMethod.POST)
	public void endConsegna(@RequestHeader("Authorization") String authorization, @RequestBody EndConsegnaDTO consegna){
		
		String codice = consegna.getCodiceritiro();
		LocalTime orario = consegna.getOrario();
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		corriereService.endConsegna(username, codice,orario);
	}
	
	@RequestMapping(value = "corriere/getPuntiRitiro", method = RequestMethod.GET)
	public List<Puntoritiro> getPuntiRitiro(@RequestHeader("Authorization") String authorization){
		String username = null;
        String jwt = authorization.substring(7);
        username = jwtUtil.extractUsername(jwt);
		return corriereService.getPuntiRitiro(username);
	}
	
	
}
