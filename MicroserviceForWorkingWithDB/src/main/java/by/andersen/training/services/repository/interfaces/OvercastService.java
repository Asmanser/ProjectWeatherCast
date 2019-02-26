package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.Overcast;

import java.util.List;

public interface OvercastService extends CrudService<Overcast, Long> {

    List<Overcast> findWithAllLazyAll();

    Overcast findWithAllLazyById(Long id);

}
