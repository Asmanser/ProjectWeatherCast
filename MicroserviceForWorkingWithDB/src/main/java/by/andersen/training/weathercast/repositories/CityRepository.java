package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.City;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository  extends CrudRepository<City, Long> {

    @EntityGraph(attributePaths = {"country"})
    Iterable<City> findAll();

    @EntityGraph(attributePaths = {"country"})
    Optional<City> findLazyById(Long aLong);
}
