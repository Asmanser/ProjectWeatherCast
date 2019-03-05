package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.WeatherClothing;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherClothingRepository extends CrudRepository<WeatherClothing, Long> {

    @EntityGraph(attributePaths = {"outerWear","underWear","footWear","cap","accessory","outerWear.image",
    "underWear.image","footWear.image","cap.image","accessory.image"})
    WeatherClothing findLazyById(Long aLong);

    @EntityGraph(attributePaths = {"outerWear","underWear","footWear","cap","accessory","outerWear.image",
            "underWear.image","footWear.image","cap.image","accessory.image"})
    Iterable<WeatherClothing> findAll();

}
