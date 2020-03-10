package guru.springframework.model.events;

import java.io.Serializable;

import guru.springframework.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable{

	private static final long serialVersionUID = 7276411188825206258L;
	
	private BeerDto beerDto;

}
