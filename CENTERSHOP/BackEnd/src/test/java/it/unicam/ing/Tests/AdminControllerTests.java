package it.unicam.ing.Tests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import it.unicam.ing.helper.ConsegnaHelper;
import it.unicam.ing.models.Commerciante;
import it.unicam.ing.models.Corriere;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.DTO.*;
import it.unicam.ing.repository.CommercianteRepository;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.ProdottoRepository;



@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private CommercianteRepository commercianteRepository;
	
	@Autowired
	private ConsegnaHelper consegnaHelper;

	@Test
	public void shouldReturn403() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void givenVia_shouldReturnTrue_IfViaIsInCenter() throws Exception {
		String stringTrue = "{\r\n    \"via\" : \"Via Rossi \",\r\n    \"posizione\" : \"1234\"\r\n}";
		String stringFalse= "{\r\n    \"via\" : \"Via Cipolla \",\r\n    \"posizione\" : \"1234\"\r\n}";
		this.mockMvc.perform(post("/admin/checkRitiro").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"))
				.andExpect(status().isOk()).andExpect(content().string("true"));
		this.mockMvc.perform(post("/admin/checkRitiro").contentType(MediaType.APPLICATION_JSON).content(stringFalse).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"))
		.andExpect(status().isOk()).andExpect(content().string("false"));
	}
	
	@Test
	public void givenConsegna_shouldReturnTheRightConsegnaDTO() throws Exception {
		Prodotto p = prodottoRepository.findById("6VdX1ca4MmiU7z3AzD4AHSC7aOtM2j").get();
		String jsonString="{\r\n    \"prodotti\": [\"6VdX1ca4MmiU7z3AzD4AHSC7aOtM2j\"],\r\n    \"puntoRitiro\" : \"Via Rossi \",\r\n    \"nominativo\" : \"Paolo Mazzanti\"\r\n}";
		Corriere c = consegnaHelper.findCorriereDisp();
		this.mockMvc.perform(post("/admin/newConsegna").contentType(MediaType.APPLICATION_JSON).content(jsonString)
				.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"));
		Corriere c1 = corriereRepository.findByUsername(c.getUsername());
		Prodotto p1 = prodottoRepository.findById("6VdX1ca4MmiU7z3AzD4AHSC7aOtM2j").get();
		assertThat(p.getDisponibilita()-1==p1.getDisponibilita()).isTrue();
		assertThat(c.getOrariodisponibilita().plusMinutes(20).equals(c1.getOrariodisponibilita())).isTrue();
		
	}
	
	@Test
	public void givenCommerciante_shouldCreateCommerciante() throws Exception {
		Commerciante c = commercianteRepository.findByUsername("Paolaaa");
		assertThat(c==null).isTrue();
		String stringTrue = "{\r\n    \"username\" : \"Paolaaa\",\r\n    \"password\" : \"Mancini\",\r\n    \"negozio\" : \"Magia\",\r\n    \"via\" : \"Via Magia\"\r\n   \r\n}";
		this.mockMvc.perform(post("/admin/newCommerciante").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"))
				.andExpect(status().isOk());
		Commerciante c1 = commercianteRepository.findByUsername("Paolaaa");
		assertThat(c1!=null).isTrue();
	}
	
	
}
