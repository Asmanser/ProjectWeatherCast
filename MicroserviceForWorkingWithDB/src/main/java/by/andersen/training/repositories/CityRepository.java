package by.andersen.training.repositories;

import by.andersen.training.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository  extends CrudRepository<City, Long> {
}
