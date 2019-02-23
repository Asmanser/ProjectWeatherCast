package by.andersen.training.repositories;

import by.andersen.training.models.WeatherInformation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherInformationRepository extends CrudRepository<WeatherInformation, Long> {

    @EntityGraph(attributePaths = {"city","directionWind","overcast","weatherCondition","weatherClothing",
    "overcast,image","weatherCondition,image"})
    Iterable<WeatherInformation> findWithAllLazyAll();

    @EntityGraph(attributePaths = {"city","directionWind","overcast","weatherCondition","weatherClothing",
            "overcast,image","weatherCondition,image"})
    Optional<WeatherInformation> findWithAllLazyById(Long id);

}
