package guru.springframework.msscbeerservice.web.service.Inventory;

import java.util.UUID;

public interface BeerInventoryService {
	
	Integer getOnhandInventory(UUID beerId);

}
