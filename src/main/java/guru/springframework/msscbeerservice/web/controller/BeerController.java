package guru.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.service.BeerService;

@RestController
@RequestMapping("api/v1/beer")
public class BeerController {

	@Autowired
	private BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> handlePost(@Validated @RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.saveBeer(beerDto), HttpStatus.CREATED);

	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> handleUpdate(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {

		return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);

	}

}
