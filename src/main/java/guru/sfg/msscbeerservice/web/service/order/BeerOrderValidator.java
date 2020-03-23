package guru.sfg.msscbeerservice.web.service.order;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.msscbeerservice.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

	private final BeerRepository beerRepository;

	public Boolean validateOrder(BeerOrderDto beerOrder) {

		AtomicInteger beersNotFound = new AtomicInteger();

		beerOrder.getBeerOrderLines()
					.forEach(orderline -> {
												if (beerRepository.findByUpc(orderline.getUpc()) == null) {
													beersNotFound.incrementAndGet();
										}
		});

		return beersNotFound.get() == 0;
	}

}