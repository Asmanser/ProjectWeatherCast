package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.City;

public interface CityService extends CrudService<City, Long> {

    Iterable<City> findWithAllLazyAll();

    City findWithAllLazyById(Long id);

}
