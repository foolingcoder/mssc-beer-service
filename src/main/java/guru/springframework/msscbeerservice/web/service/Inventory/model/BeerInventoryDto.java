package guru.springframework.msscbeerservice.web.service.Inventory.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto {
	private UUID id;
	private OffsetDateTime createdDate;
	private OffsetDateTime lastModifiedDate;
	private UUID beerId;
	private Integer quantityOnHand;
}