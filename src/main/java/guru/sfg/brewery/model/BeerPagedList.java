package guru.sfg.brewery.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class BeerPagedList extends PageImpl<BeerDto> implements Serializable{

	private static final long serialVersionUID = 1L;

	public BeerPagedList(List<BeerDto> content) {
		super(content);

	}
	
	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable,total);
		
	}
	
	public BeerPagedList(   @JsonProperty("content") List<BeerDto> content, 
							@JsonProperty("number") int number,
							@JsonProperty("size") int size,
							@JsonProperty("totalElements") Long totalElements,
							@JsonProperty("pageable") JsonNode pageable, 
							@JsonProperty("last") boolean last,
							@JsonProperty("totalPages") int totalPages,
							@JsonProperty("sort") JsonNode sort,
							@JsonProperty("first") boolean first,
							@JsonProperty("numberOfElements") int numberOfElements    ) {
		
		super(content, PageRequest.of(number, size)  ,totalElements);
		
	}


}
