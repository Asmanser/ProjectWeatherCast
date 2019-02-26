package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherInformation;

import java.util.List;

public interface WeatherInformationService extends CrudService<WeatherInformation, Long> {

    List<WeatherInformation> findWithAllLazyAll();

    WeatherInformation findWithAllLazyById(Long id);
}
