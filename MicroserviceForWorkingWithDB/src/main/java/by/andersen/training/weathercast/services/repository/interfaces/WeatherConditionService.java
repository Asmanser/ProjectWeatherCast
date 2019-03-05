package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.WeatherCondition;

import java.util.List;

public interface WeatherConditionService extends CrudService<WeatherCondition, Long> {

    List<WeatherCondition> findWithAllLazyAll();

    WeatherCondition findWithAllLazyById(Long id);

}
