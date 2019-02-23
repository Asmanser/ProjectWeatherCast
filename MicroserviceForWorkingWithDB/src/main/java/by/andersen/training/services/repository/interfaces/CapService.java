package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.Cap;

public interface CapService extends CrudService<Cap, Long> {

    Iterable<Cap> findWithAllLazyAll();

    Cap findWithAllLazyById(Long id);

}
