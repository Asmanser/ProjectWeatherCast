package by.andersen.training.repositories;

import by.andersen.training.models.WeatherClothing;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherClothingRepository extends CrudRepository<WeatherClothing, Long> {

    @EntityGraph(attributePaths = {"outerWear","underWear","footWear","cap","accessory","outerWear.image",
    "underWear.image","footWear.image","cap.image","accessory.image"})
    WeatherClothing findLazyById(Long aLong);

    @EntityGraph(attributePaths = {"outerWear","underWear","footWear","cap","accessory","outerWear.image",
            "underWear.image","footWear.image","cap.image","accessory.image"})
    Iterable<WeatherClothing> findAll();

}
