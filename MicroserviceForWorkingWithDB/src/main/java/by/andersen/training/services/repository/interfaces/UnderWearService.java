package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.UnderWear;

public interface UnderWearService extends CrudService<UnderWear, Long> {

    Iterable<UnderWear> findWithAllLazyAll();

    UnderWear findWithAllLazyById(Long id);

}
