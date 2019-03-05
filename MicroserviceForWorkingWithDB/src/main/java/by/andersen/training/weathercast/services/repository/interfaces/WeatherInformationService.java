package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.WeatherInformation;

import java.util.List;

public interface WeatherInformationService extends CrudService<WeatherInformation, Long> {

    List<WeatherInformation> findWithAllLazyAll();

    WeatherInformation findWithAllLazyById(Long id);
}
