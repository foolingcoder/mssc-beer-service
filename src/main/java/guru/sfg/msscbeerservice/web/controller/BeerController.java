package guru.sfg.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEnum;
import guru.sfg.msscbeerservice.web.service.BeerService;

@RestController
@RequestMapping("api/v1/")
public class BeerController {

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	@Autowired
	private BeerService beerService;

	@GetMapping(path="beer" , produces ="application/json")
	public ResponseEntity<BeerPagedList> listBeers(
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "beerName", required = false) String beerName,
			@RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
			@RequestParam(value = "showInventoryOnhand", required = false) Boolean showInventoryOnhand) {
		
		if (showInventoryOnhand == null ) {
			showInventoryOnhand = false;
		}

		
		if (pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
        BeerPagedList beerPagedList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),showInventoryOnhand);

		return new ResponseEntity<>(beerPagedList,HttpStatus.OK);
	}

	@GetMapping(value= "beer/{beerId}" )
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
			                               @RequestParam(value = "showInventoryOnhand", required = false) Boolean showInventoryOnhand) {

		if (showInventoryOnhand == null ) {
			showInventoryOnhand = false;
		}

		return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnhand), HttpStatus.OK);
	}
	
	@GetMapping("beerUpc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc) {
		return new ResponseEntity<>(beerService.getByUpc(upc), HttpStatus.OK);
	}


	@PostMapping(path="beer")
	public ResponseEntity<BeerDto> saveBeer(@Validated @RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.saveBeer(beerDto), HttpStatus.CREATED);

	}

	@PutMapping("beer/{beerId}")
	public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId,
			@Validated @RequestBody BeerDto beerDto) {

		return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);

	}

}
