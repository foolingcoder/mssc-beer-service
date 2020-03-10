package guru.sfg.msscbeerservice.web.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEnum;

public interface BeerService {

	BeerDto getBeerById(UUID beerId, Boolean showInventoryOnhand);

	BeerDto saveBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnhand);

	BeerDto getByUpc(String upc);

}
