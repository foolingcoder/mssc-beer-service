package guru.sfg.msscbeerservice.web.service.Inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("local-discovery")
@RequiredArgsConstructor
@Slf4j
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService {

	private final InventoryFeignClient inventoryFeignClient;

	@Override
	public Integer getOnhandInventory(UUID beerId) {
		
		log.debug("Calling Inventory Service FEIGN client: " +inventoryFeignClient);
		ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryFeignClient.getOnhandInventory(beerId);

		Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
				.mapToInt(BeerInventoryDto::getQuantityOnHand).sum();

		return onHand;
	}

}
