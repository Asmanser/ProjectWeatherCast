package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherCondition;

import java.util.List;

public interface WeatherConditionService extends CrudService<WeatherCondition, Long> {

    List<WeatherCondition> findWithAllLazyAll();

    WeatherCondition findWithAllLazyById(Long id);

}
