package it.unicam.ing.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.ConsegnaAdminDTO;
import it.unicam.ing.DTO.ConsegnaFrontEndDTO;
import it.unicam.ing.DTO.PagamentoDTO;
import it.unicam.ing.DTO.PuntoRitiroDTO;
import it.unicam.ing.DTO.RegisterCommercianteDTO;
import it.unicam.ing.DTO.RegisterCorriereDTO;
import it.unicam.ing.helper.ConsegnaHelper;
import it.unicam.ing.models.Cliente;
import it.unicam.ing.models.Commerciante;
import it.unicam.ing.models.Consegna;
import it.unicam.ing.models.Corriere;
import it.unicam.ing.models.MyUser;
import it.unicam.ing.models.MyUser.Role;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.models.Puntoritiro;
import it.unicam.ing.repository.ClienteRepository;
import it.unicam.ing.repository.CommercianteRepository;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.ProdottoRepository;
import it.unicam.ing.repository.PuntoritiroRepository;
import it.unicam.ing.repository.UserRepository;
import it.unicam.ing.serviceInterface.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private CommercianteRepository commercianteRepository;
		
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PuntoritiroRepository puntoRitiroRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConsegnaHelper consegnaHelper;
	
	@Autowired
	private PasswordEncoder pass;
	


	public ConsegnaFrontEndDTO newConsegna(ConsegnaAdminDTO consegnaDTO) {
		Corriere c = consegnaHelper.findCorriereDisp();
		double importo = consegnaHelper.findImporto(consegnaDTO);
		int negozi = consegnaHelper.findNumeroNegozi(consegnaDTO);
		List<Prodotto> list = new ArrayList<Prodotto>();
		for (String id : consegnaDTO.getProdotti()) {
			list.add(prodottoRepository.findById(id).get());
			decreaseDisponibilita(id);
		}
		
		Consegna consegna = new Consegna(importo,consegnaDTO.getPuntoRitiro(),c.getOrariodisponibilita().plusMinutes(20*negozi),consegnaDTO
					.getNominativo(),list);
		c.getConsegne().add(consegna);		
		c.setOrariodisponibilita(c.getOrariodisponibilita().plusMinutes(20*negozi));
		corriereRepository.save(c);
		ConsegnaFrontEndDTO conDTO = new ConsegnaFrontEndDTO(consegna.getOra(),consegna.getCodiceritiro());
		return conDTO;
	}
	
	
	private void decreaseDisponibilita(String id) {
		Prodotto p = prodottoRepository.findById(id).get();
		p.setDisponibilita(p.getDisponibilita()-1);
		prodottoRepository.save(p);
	}

	public boolean checkPayment(PagamentoDTO payment) {
		return paymentService.checkPayment(payment);
	}

	public boolean checkIfIsInCenter(PuntoRitiroDTO puntoRitiro) {
		
		String via = puntoRitiro.getVia();
		String via3 = via.substring(0,via.length()-1);
		String via1 = via.substring(0,via.length()-2);
		String via2 = via.substring(0, via.length()-3);
		List<Puntoritiro> list = puntoRitiroRepository.findByPosizione("Centro");
		for (Puntoritiro pr : list) {
			if(pr.getVia().equalsIgnoreCase(via1)|| pr.getVia().equalsIgnoreCase(via2) || pr.getVia().equalsIgnoreCase(via3)|| pr.getVia().equalsIgnoreCase(via))
				return true;
		}
		return false;
	}

	public void newCliente(String id) {
		Cliente c = new Cliente(id,21);
		clienteRepository.save(c);
		
	}
	

	public void updateTimeLeft(String id) {
		Optional<Cliente> c = clienteRepository.findById(id);
		if(c.isEmpty()) 
			clienteRepository.save(new Cliente(id,30));
		else {
		c.get().setTimeleft(30);
		clienteRepository.save(c.get());
		}
	}


	public void timePassed() {
		Iterable<Cliente> list = clienteRepository.findAll();
		for (Cliente cliente : list) {
			if(cliente.getTimeleft()-20<=0)
				clienteRepository.deleteById(cliente.getId());
			else  {
				cliente.setTimeleft(cliente.getTimeleft()-20);
				clienteRepository.save(cliente);
			}
		}
		
	}


	public void newCommerciante(RegisterCommercianteDTO login) {
		userRepository.save(new MyUser(login.getUsername(),pass.encode(login.getPassword()), Role.COMMERCIANTE));
		commercianteRepository.save(new Commerciante(login.getUsername(),login.getNegozio(),login.getVia()));	
	}
	
	public void newCorriere(RegisterCorriereDTO login) {
		userRepository.save(new MyUser(login.getUsername(),pass.encode(login.getPassword()), Role.CORRIERE));
		corriereRepository.save(new Corriere(login.getUsername(), LocalTime.parse("08:00") ,login.getAzienda()));	
	}
	
	
	
}
