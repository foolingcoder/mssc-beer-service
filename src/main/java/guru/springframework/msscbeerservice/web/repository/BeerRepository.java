package guru.springframework.msscbeerservice.web.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.msscbeerservice.web.domain.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}
