package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.Overcast;

import java.util.List;

public interface OvercastService extends CrudService<Overcast, Long> {

    List<Overcast> findWithAllLazyAll();

    Overcast findWithAllLazyById(Long id);

}
