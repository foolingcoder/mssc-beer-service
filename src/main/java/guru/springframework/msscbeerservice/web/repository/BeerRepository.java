package guru.springframework.msscbeerservice.web.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import guru.springframework.msscbeerservice.web.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
