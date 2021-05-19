package it.unicam.ing.Tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import it.unicam.ing.DTO.LoginResponseDTO;
import it.unicam.ing.helper.ConsegnaHelper;
import it.unicam.ing.models.Consegna;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.repository.CommercianteRepository;
import it.unicam.ing.repository.ConsegnaRepository;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.ProdottoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CorriereControllerTests {
		
	private static String token;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private CommercianteRepository commercianteRepository;
	
	@Autowired
	private ConsegnaRepository consegnaRepository;
	
	@Autowired
	private ConsegnaHelper consegnaHelper;
	
	
	
	
	
	@Test
	@BeforeTestClass
	public void givenUsernameAndPass_shouldLogin_IfIsRight() throws Exception {
		String stringTrue = "{\r\n    \"username\" : \"Carlo1976\",\r\n    \"password\" : \"Barto1234\"\r\n}";
		MvcResult mvcResult = this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"))
				.andExpect(status().isOk()).andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		String ruolo = actualResponseBody.substring(155 , actualResponseBody.length()-2);
		token = actualResponseBody.substring(8,  actualResponseBody.length()-21);
		
		assertThat(ruolo.equals("CORRIERE"));		
	}
	
	@Test
	public void shouldReturn403() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError());
	}
	
	
	@Test
	public void givenVia_shouldReturnTrue_IfViaIsInCenter() throws Exception {
		System.out.println(token);
		String stringTrue = "{\r\n    \"via\" : \"Via Rossi \",\r\n    \"posizione\" : \"1234\"\r\n}";
		MvcResult mvcResult = this.mockMvc.perform(get("/corriere/getPuntiRitiro").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer ".concat(token)))
				.andExpect(status().isOk()).andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponseBody.contains("Via Rossi")==true).isTrue();
		assertThat(actualResponseBody.contains("Via Blu")==false).isTrue();
		assertThat(actualResponseBody.contains("Via Neri")==true).isTrue();
		assertThat(actualResponseBody.contains("Via Miracolini")==true).isTrue();
	
		
	}
	
	@Test
	public void givenId_shouldDeleteConsegna() throws Exception {
		Consegna c = new Consegna(50.0,"via ciao",LocalTime.now(),"carlo",new ArrayList<Prodotto>());
		this.consegnaRepository.save(c);
		String cod = c.getCodiceritiro();
		String stringTrue = "{\r\n    \"codiceritiro\" : \""+cod+" \",\r\n    \"orario\" : \"09:30\"\r\n}";
		this.mockMvc.perform(post("/corriere/endConsegna").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer ".concat(token)))
				.andExpect(status().isOk());		
		assertThat(consegnaRepository.findByCodiceritiro(cod)==null).isTrue();
	}
	
	
	
	
	
	
	
	
	
}
