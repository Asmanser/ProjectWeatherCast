package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.DirectionWind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionWindRepository extends CrudRepository<DirectionWind, Long> {
}
