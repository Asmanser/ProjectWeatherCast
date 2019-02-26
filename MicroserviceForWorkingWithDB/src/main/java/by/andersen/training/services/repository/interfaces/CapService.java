package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.Cap;

import java.util.List;

public interface CapService extends CrudService<Cap, Long> {

    List<Cap> findWithAllLazyAll();

    Cap findWithAllLazyById(Long id);

}
