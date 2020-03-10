package guru.springframework.model.events;

import guru.springframework.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

	private static final long serialVersionUID = -2926966028391559087L;
	
	public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }

}
