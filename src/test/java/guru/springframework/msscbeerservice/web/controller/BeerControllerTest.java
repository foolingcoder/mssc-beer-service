package guru.springframework.msscbeerservice.web.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.repository.BeerRepository;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	public MockMvc mockMvc;	

    @Autowired
    private  ObjectMapper objectMapper;    

    @MockBean
	private BeerRepository beerRepository;
	

	
}
