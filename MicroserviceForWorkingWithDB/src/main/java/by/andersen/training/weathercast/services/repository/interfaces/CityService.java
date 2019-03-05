package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.City;

import java.util.List;

public interface CityService extends CrudService<City, Long> {

    List<City> findWithAllLazyAll();

    City findWithAllLazyById(Long id);

}
