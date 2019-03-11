package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.WeatherInformation;

import java.sql.Date;
import java.util.List;

public interface WeatherInformationService extends CrudService<WeatherInformation, Long> {

    List<WeatherInformation> findWithAllLazyAll();

    WeatherInformation findWithAllLazyById(Long id);

    List<WeatherInformation> findLazyByDate(Date date);

    WeatherInformation findLazyByDateAndCity(Date date, City city);

}
