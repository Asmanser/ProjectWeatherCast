package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherInformation;

public interface WeatherInformationService extends CrudService<WeatherInformation, Long> {

    Iterable<WeatherInformation> findWithAllLazyAll();

    WeatherInformation findWithAllLazyById(Long id);
}
