package guru.springframework.msscbeerservice.web.service;

import java.util.UUID;

import guru.springframework.msscbeerservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

	BeerDto saveBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

}
