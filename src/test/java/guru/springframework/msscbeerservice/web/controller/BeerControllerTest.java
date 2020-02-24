package guru.springframework.msscbeerservice.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.web.service.BeerService;

@WebMvcTest(controllers = BeerController.class)
public class BeerControllerTest {

	@Autowired
	private MockMvc mockMvc;	

    @Autowired
    private  ObjectMapper objectMapper;    

    @MockBean
	private BeerService beerService;
   
	
	public BeerDto getValidBeer() {
		return BeerDto.builder()
				     		.beerName("Beer1")
				     		.beerStyle(BeerStyleEnum.PALE_ALE)
							.upc(123456789012L)
							.price(new BigDecimal("10.99"))
							.build();
	}
    
	@Test
	public void getBeer() throws Exception {
		given(beerService.getBeerById(any(UUID.class))).willReturn(getValidBeer());

		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.beerName", is(getValidBeer().getBeerName())));
	}
	
	@Test
	public void handlePost() throws Exception {
		// given
		BeerDto beerDto = getValidBeer();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        
        //saved beer
        given(beerService.saveBeer(any())).willReturn(beerDto);
		
		mockMvc.perform(post("/api/v1/beer/")
			    .content(beerDtoJson)
				.contentType(MediaType.APPLICATION_JSON))	
	            .andExpect(status().isCreated());
	}


	@Test
	public void handleUpdate() throws Exception {
		// given
		BeerDto beerDto = getValidBeer();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		// when
		mockMvc.perform(
				put("/api/v1/beer/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isNoContent());

		then(beerService).should().updateBeer(any(), any());

	}
	


}
