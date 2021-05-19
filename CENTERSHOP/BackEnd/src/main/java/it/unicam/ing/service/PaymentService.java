package it.unicam.ing.service;

import org.springframework.stereotype.Service;

import it.unicam.ing.DTO.PagamentoDTO;

@Service
public class PaymentService {

	public boolean checkPayment(PagamentoDTO payment) {
		return true;
	}

}
