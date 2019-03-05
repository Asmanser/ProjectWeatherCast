package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.WeatherCondition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherConditionRepository extends CrudRepository<WeatherCondition, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<WeatherCondition> findAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<WeatherCondition> findLazyById(Long id);

}
