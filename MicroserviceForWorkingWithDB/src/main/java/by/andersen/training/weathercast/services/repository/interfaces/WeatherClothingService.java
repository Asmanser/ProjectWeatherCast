package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.WeatherClothing;

import java.util.List;

public interface WeatherClothingService extends CrudService<WeatherClothing, Long> {

    List<WeatherClothing> findWithAllLazyAll();

    WeatherClothing findWithAllLazyById(Long id);

}
