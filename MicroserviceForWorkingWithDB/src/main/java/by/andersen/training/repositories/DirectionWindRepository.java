package by.andersen.training.repositories;

import by.andersen.training.models.DirectionWind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionWindRepository extends CrudRepository<DirectionWind, Long> {
}
