package it.unicam.ing.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ing.DTO.ClienteDTO;
import it.unicam.ing.DTO.ConsegnaAdminDTO;
import it.unicam.ing.DTO.ConsegnaFrontEndDTO;
import it.unicam.ing.DTO.PagamentoDTO;
import it.unicam.ing.DTO.PuntoRitiroDTO;
import it.unicam.ing.DTO.RegisterCommercianteDTO;
import it.unicam.ing.DTO.RegisterCorriereDTO;
import it.unicam.ing.serviceInterface.IAdminService;

@RestController
public class AdminController {

	
	@Autowired
	private IAdminService adminService;
	
		
	@RequestMapping(value = "/admin/newConsegna", method = RequestMethod.POST)
	public ConsegnaFrontEndDTO addConsegna(@RequestBody ConsegnaAdminDTO consegna){
		return adminService.newConsegna(consegna);
	}
	
	@RequestMapping(value = "/admin/payment", method = RequestMethod.POST)
	public boolean checkPayment(@RequestBody PagamentoDTO payment){
		return adminService.checkPayment(payment);
	}
	
	
	@RequestMapping(value = "/admin/checkRitiro", method = RequestMethod.POST)
	public boolean checkIfIsInCenter(@RequestBody PuntoRitiroDTO puntoRitiro){
		return adminService.checkIfIsInCenter(puntoRitiro);
	}
	
	
	@RequestMapping(value = "/admin/cliente", method = RequestMethod.POST)
	public void addCliente(@RequestBody ClienteDTO cliente){
		adminService.newCliente(cliente.getId());
	}
	
	@PutMapping(value = "/admin/time")
	public void updateTime(){
		adminService.timePassed();
	}
	
	@RequestMapping(value = "/admin/newCommerciante", method = RequestMethod.POST)
	public void insertCommerciante(@RequestBody RegisterCommercianteDTO login){
		adminService.newCommerciante(login);
	}
	
	@RequestMapping(value = "/admin/newCorriere", method = RequestMethod.POST)
	public void insertCorriere(@RequestBody RegisterCorriereDTO login){
		adminService.newCorriere(login);
	}
	
	
	
	
	
}
