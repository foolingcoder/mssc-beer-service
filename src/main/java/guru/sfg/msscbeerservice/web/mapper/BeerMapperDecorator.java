package guru.sfg.msscbeerservice.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.msscbeerservice.web.domain.Beer;
import guru.sfg.msscbeerservice.web.service.Inventory.BeerInventoryService;

public abstract class BeerMapperDecorator implements BeerMapper {

	private BeerInventoryService beerInventoryService;
	private BeerMapper mapper;

	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}

	@Autowired
	public void setMapper(BeerMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
    public BeerDto beerToBeerDto(Beer beer) {
       return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }


	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return mapper.beerDtoToBeer(beerDto);
	}

}
