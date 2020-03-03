package guru.springframework.msscbeerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import guru.springframework.msscbeerservice.web.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);	

    BeerDto beerToBeerDtoWithInventory(Beer beer);

}
