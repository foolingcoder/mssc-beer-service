package guru.springframework.msscbeerservice.web.model.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.web.model.BeerDto;



@RestController
@RequestMapping("api/v1/beer")
public class BeerController {

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		//TODO
		return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto) {
		BeerDto savedDto = null;
		//TODO
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);

	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		//TODO
		return new ResponseEntity<HttpHeaders>(HttpStatus.NO_CONTENT);

	}

}
