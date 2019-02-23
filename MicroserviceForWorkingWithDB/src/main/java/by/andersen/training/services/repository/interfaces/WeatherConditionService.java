package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.WeatherCondition;

public interface WeatherConditionService extends CrudService<WeatherCondition, Long> {

    Iterable<WeatherCondition> findWithAllLazyAll();

    WeatherCondition findWithAllLazyById(Long id);

}
