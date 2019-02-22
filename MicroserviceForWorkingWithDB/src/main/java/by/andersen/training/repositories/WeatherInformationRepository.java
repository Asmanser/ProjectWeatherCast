package by.andersen.training.repositories;

import by.andersen.training.models.WeatherInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInformationRepository extends CrudRepository<WeatherInformation, Long> {
}
