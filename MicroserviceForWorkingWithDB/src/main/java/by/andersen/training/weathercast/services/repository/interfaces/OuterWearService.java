package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.OuterWear;

import java.util.List;

public interface OuterWearService extends CrudService<OuterWear, Long> {

    List<OuterWear> findWithAllLazyAll();

    OuterWear findWithAllLazyById(Long id);

}
