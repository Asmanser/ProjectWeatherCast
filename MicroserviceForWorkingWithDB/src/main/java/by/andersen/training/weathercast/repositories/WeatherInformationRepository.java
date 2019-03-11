package by.andersen.training.weathercast.repositories;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.WeatherInformation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface WeatherInformationRepository extends CrudRepository<WeatherInformation, Long> {

    @EntityGraph(attributePaths = {"city","city.country","directionWind","overcast","weatherCondition",
            "weatherClothing", "overcast.image","weatherCondition.image","weatherClothing","weatherClothing",
            "weatherClothing.outerWear","weatherClothing.underWear","weatherClothing.footWear",
            "weatherClothing.cap","weatherClothing.accessory","weatherClothing.outerWear.image",
            "weatherClothing.underWear.image","weatherClothing.footWear.image","weatherClothing.cap.image",
            "weatherClothing.accessory.image"})
    Iterable<WeatherInformation> findAll();

    @EntityGraph(attributePaths = {"city","city.country","directionWind","overcast","weatherCondition",
            "weatherClothing", "overcast.image","weatherCondition.image","weatherClothing","weatherClothing",
            "weatherClothing.outerWear","weatherClothing.underWear","weatherClothing.footWear",
            "weatherClothing.cap","weatherClothing.accessory","weatherClothing.outerWear.image",
            "weatherClothing.underWear.image","weatherClothing.footWear.image","weatherClothing.cap.image",
            "weatherClothing.accessory.image"})
    Optional<WeatherInformation> findLazyById(Long id);

    @EntityGraph(attributePaths = {"city","city.country","directionWind","overcast","weatherCondition",
            "weatherClothing", "overcast.image","weatherCondition.image","weatherClothing","weatherClothing",
            "weatherClothing.outerWear","weatherClothing.underWear","weatherClothing.footWear",
            "weatherClothing.cap","weatherClothing.accessory","weatherClothing.outerWear.image",
            "weatherClothing.underWear.image","weatherClothing.footWear.image","weatherClothing.cap.image",
            "weatherClothing.accessory.image"})
    Iterable<WeatherInformation> findLazyByDate(Date date);

    @EntityGraph(attributePaths = {"city","city.country","directionWind","overcast","weatherCondition",
            "weatherClothing", "overcast.image","weatherCondition.image","weatherClothing","weatherClothing",
            "weatherClothing.outerWear","weatherClothing.underWear","weatherClothing.footWear",
            "weatherClothing.cap","weatherClothing.accessory","weatherClothing.outerWear.image",
            "weatherClothing.underWear.image","weatherClothing.footWear.image","weatherClothing.cap.image",
            "weatherClothing.accessory.image"})
    Optional<WeatherInformation> findLazyByDateAndCity(Date date, City city);

}
