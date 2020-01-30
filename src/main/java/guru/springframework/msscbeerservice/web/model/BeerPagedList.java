package guru.springframework.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;

public class BeerPagedList extends PageImpl<BeerDto>{

	public BeerPagedList(List<BeerDto> content) {
		super(content);

	}

}
