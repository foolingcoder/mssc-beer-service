package guru.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	@Null
	private UUID id;
	
	@Null
	private Integer version;

	@Null
	private OffsetDateTime createdDate;

	@Null
	private OffsetDateTime lastModifiedDate;

	@NotEmpty
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyle;

	@NotNull
	private String upc;

	@JsonFormat(shape=Shape.STRING)
	@Positive
	@NotNull
	private BigDecimal price;

	private Integer quantityOnHand;

}
