package guru.sfg.msscbeerservice.web.service.Inventory;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import guru.sfg.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryFeignClient {

	private final InventoryFeignFailoverClient inventoryFeignFailoverClient;

	@Override
	public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
		return inventoryFeignFailoverClient.getOnhandInventory();
	}
}