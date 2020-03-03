package guru.springframework.msscbeerservice.web.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.domain.Beer;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
	@Override
	public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnhand) {
		if(showInventoryOnhand) {
		   return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}else {
		  return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beer);
	}

	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnhand == false ")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnhand) {

		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);

		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);

		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);

		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}
		
		BeerPagedList beerPagedList;
		
		if(showInventoryOnhand) {

		 beerPagedList = new BeerPagedList(
				 		beerPage.getContent()
				        .stream()
				        .map(beerMapper::beerToBeerDtoWithInventory)
				        .collect(Collectors.toList()),
						PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
						beerPage.getTotalElements());
		}else {
			
			 beerPagedList = new BeerPagedList(
				 		beerPage.getContent()
				        .stream()
				        .map(beerMapper::beerToBeerDto)
				        .collect(Collectors.toList()),
						PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
						beerPage.getTotalElements());
		}

		return beerPagedList;
	}

}
