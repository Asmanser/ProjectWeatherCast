package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.UnderWear;

import java.util.List;

public interface UnderWearService extends CrudService<UnderWear, Long> {

    List<UnderWear> findWithAllLazyAll();

    UnderWear findWithAllLazyById(Long id);

}
