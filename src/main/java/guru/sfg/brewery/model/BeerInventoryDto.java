package guru.sfg.brewery.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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