package by.andersen.training.repositories;

import by.andersen.training.models.WeatherCondition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherConditionRepository extends CrudRepository<WeatherCondition, Long> {

    @EntityGraph(attributePaths = {"image"})
    Iterable<WeatherCondition> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"image"})
    Optional<WeatherCondition> findWithAllLazyById(Long id);

}
