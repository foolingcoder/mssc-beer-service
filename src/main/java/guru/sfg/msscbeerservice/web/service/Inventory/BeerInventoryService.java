package guru.sfg.msscbeerservice.web.service.Inventory;

import java.util.UUID;

public interface BeerInventoryService {
	
	Integer getOnhandInventory(UUID beerId);

}
