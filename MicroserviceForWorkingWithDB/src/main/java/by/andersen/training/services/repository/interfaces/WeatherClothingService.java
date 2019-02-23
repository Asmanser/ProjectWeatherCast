package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherClothing;

public interface WeatherClothingService extends CrudService<WeatherClothing, Long> {

    Iterable<WeatherClothing> findWithAllLazyAll();

    WeatherClothing findWithAllLazyById(Long id);

}
