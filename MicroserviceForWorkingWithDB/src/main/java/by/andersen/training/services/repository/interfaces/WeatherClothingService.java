package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherClothing;

import java.util.List;

public interface WeatherClothingService extends CrudService<WeatherClothing, Long> {

    List<WeatherClothing> findWithAllLazyAll();

    WeatherClothing findWithAllLazyById(Long id);

}
