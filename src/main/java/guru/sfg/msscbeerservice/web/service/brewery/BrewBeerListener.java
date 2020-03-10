package guru.sfg.msscbeerservice.web.service.brewery;

import javax.transaction.Transactional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.events.BrewBeerEvent;
import guru.sfg.brewery.model.events.NewInventoryEvent;
import guru.sfg.msscbeerservice.config.JmsConfig;
import guru.sfg.msscbeerservice.web.domain.Beer;
import guru.sfg.msscbeerservice.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewBeerListener {

	private final BeerRepository beerRepository;

	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
	public void listen(BrewBeerEvent brewBeerEvent) {
		BeerDto beerDto = brewBeerEvent.getBeerDto();

		Beer beer = beerRepository.getOne(beerDto.getId());

		beerDto.setQuantityOnHand(beer.getQuantityToBrew());

		NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
		
		log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

		jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);

	}

}
