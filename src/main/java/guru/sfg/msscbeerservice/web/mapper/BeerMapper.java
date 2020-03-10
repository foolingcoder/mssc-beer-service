package guru.sfg.msscbeerservice.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.msscbeerservice.web.domain.Beer;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);	

    BeerDto beerToBeerDtoWithInventory(Beer beer);

}
