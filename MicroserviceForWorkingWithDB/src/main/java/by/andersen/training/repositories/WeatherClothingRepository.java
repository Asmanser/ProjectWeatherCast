package by.andersen.training.repositories;

import by.andersen.training.models.WeatherClothing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherClothingRepository extends CrudRepository<WeatherClothing, Long> {
}
