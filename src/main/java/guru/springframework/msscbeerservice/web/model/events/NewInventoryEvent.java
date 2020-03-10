package guru.springframework.msscbeerservice.web.model.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

	private static final long serialVersionUID = -2926966028391559087L;

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}

}