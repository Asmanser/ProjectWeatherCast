package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.FootWear;

import java.util.List;

public interface FootWearService extends CrudService<FootWear, Long> {

    List<FootWear> findWithAllLazyAll();

    FootWear findWithAllLazyById(Long id);

}
