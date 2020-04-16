package guru.sfg.msscbeerservice.web.service.Inventory;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.sfg.brewery.model.BeerInventoryDto;
			
//@FeignClient(value = "inventory-service", url = "http://localhost:8082")
@FeignClient(name = "inventory-service" ,fallback = InventoryServiceFeignClientFailover.class)
public interface InventoryFeignClient {

	@RequestMapping(method= RequestMethod.GET, value=BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH_V1)
	ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);
}
