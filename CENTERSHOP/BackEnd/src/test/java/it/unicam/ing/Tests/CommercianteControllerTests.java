package it.unicam.ing.Tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import it.unicam.ing.helper.ConsegnaHelper;
import it.unicam.ing.models.Categoria;
import it.unicam.ing.models.Commerciante;
import it.unicam.ing.models.Prodotto;
import it.unicam.ing.models.Promozioni;
import it.unicam.ing.repository.CategoriaRepository;
import it.unicam.ing.repository.CommercianteRepository;
import it.unicam.ing.repository.ConsegnaRepository;
import it.unicam.ing.repository.CorriereRepository;
import it.unicam.ing.repository.ProdottoRepository;
import it.unicam.ing.repository.PromozioneRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CommercianteControllerTests {

	
private static String token;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private CorriereRepository corriereRepository;
	
	@Autowired
	private PromozioneRepository promozioniRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CommercianteRepository commercianteRepository;
	
	@Autowired
	private ConsegnaRepository consegnaRepository;
	
	@Autowired
	private ConsegnaHelper consegnaHelper;
	
	
	@Test
	@BeforeTestClass
	public void givenUsernameAndPass_shouldLogin_IfIsRight() throws Exception {
		String stringTrue = "{\r\n    \"username\" : \"Giulia1\",\r\n    \"password\" : \"Anti1234\"\r\n}";
		MvcResult mvcResult = this.mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZWEiLCJleHAiOjE2MTQyMDEyMzcsImlhdCI6MTYxNDE2NTIzN30.yR1k3LA_Rn4__nIL2Ex1cUzjy4F9HN0J5Mobxjicldg"))
				.andExpect(status().isOk()).andReturn();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		String ruolo = actualResponseBody.substring(155 , actualResponseBody.length()-2);
		token = actualResponseBody.substring(8,  actualResponseBody.length()-25);
		
		assertThat(ruolo.equals("COMMERCIANTE"));		
	}
	
	@Test
	public void shouldReturn403() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void givenId_ShouldDeleteProdotto() throws Exception {
		Categoria c = categoriaRepository.findCategoriaByNome("Antiquariato");
		Commerciante c1 = commercianteRepository.findByUsername("Giulia1");
		Promozioni p2 = promozioniRepository.findPromozioniByPercsconto(0);
		Prodotto p = new Prodotto(50.0,"bello",c,c1,p2, 3,"img");
		
		String id = p.getId();
		String stringTrue = "{\r\n    \"id\" : "+id+"}";
		this.mockMvc.perform(delete("/commerciante/deleteProdotto").contentType(MediaType.APPLICATION_JSON).content(stringTrue).header("Authorization", "Bearer ".concat(token)))
				.andExpect(status().isOk());
		Optional<Prodotto> p1 = prodottoRepository.findById(id);
		
		
		assertThat(p1.isEmpty()).isTrue();		
	}
	
	
}
