package by.andersen.training.repositories;

import by.andersen.training.models.WeatherCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionRepository extends CrudRepository<WeatherCondition, Long> {
}
