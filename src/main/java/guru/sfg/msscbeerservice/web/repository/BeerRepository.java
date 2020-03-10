package guru.sfg.msscbeerservice.web.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import guru.sfg.brewery.model.BeerStyleEnum;
import guru.sfg.msscbeerservice.web.domain.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
	
	Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, PageRequest pageRequest);

	Optional<Beer> findByUpc(String upc);

}
