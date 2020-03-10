package guru.springframework.msscbeerservice.web.service.Inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.model.inventory.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

	private final String INVENTORY_PATH_V1 = "/api/v1/beer/{beerId}/inventory";
	private final RestTemplate restTemplate;

	private String beerInventoryServiceHost;

	public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
		this.beerInventoryServiceHost = beerInventoryServiceHost;
	}

	public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Integer getOnhandInventory(UUID beerId) {
		ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
				beerInventoryServiceHost + INVENTORY_PATH_V1, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BeerInventoryDto>>() {}, beerId);
		
		Integer onHand  = Objects.requireNonNull(responseEntity.getBody())
								.stream()
								.mapToInt(BeerInventoryDto::getQuantityOnHand)
								.sum();
        
		return onHand;
	}

}
