package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.OuterWear;

public interface OuterWearService extends CrudService<OuterWear, Long> {

    Iterable<OuterWear> findWithAllLazyAll();

    OuterWear findWithAllLazyById(Long id);

}
