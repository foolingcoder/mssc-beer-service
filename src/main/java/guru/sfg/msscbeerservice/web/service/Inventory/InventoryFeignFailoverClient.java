package guru.sfg.msscbeerservice.web.service.Inventory;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.sfg.brewery.model.BeerInventoryDto;

@FeignClient(name = "inventory-failover")
public interface InventoryFeignFailoverClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventory-failover")
	ResponseEntity<List<BeerInventoryDto>> getOnhandInventory();
}
