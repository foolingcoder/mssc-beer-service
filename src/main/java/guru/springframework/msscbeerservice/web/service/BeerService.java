package guru.springframework.msscbeerservice.web.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

public interface BeerService {

	BeerDto getBeerById(UUID beerId, Boolean showInventoryOnhand);

	BeerDto saveBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnhand);

}
