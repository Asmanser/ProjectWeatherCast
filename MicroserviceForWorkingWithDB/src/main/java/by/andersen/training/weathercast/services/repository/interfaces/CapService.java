package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.Cap;

import java.util.List;

public interface CapService extends CrudService<Cap, Long> {

    List<Cap> findWithAllLazyAll();

    Cap findWithAllLazyById(Long id);

}
