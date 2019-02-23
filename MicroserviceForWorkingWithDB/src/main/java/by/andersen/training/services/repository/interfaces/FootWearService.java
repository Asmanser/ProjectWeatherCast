package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.FootWear;

public interface FootWearService extends CrudService<FootWear, Long> {

    Iterable<FootWear> findWithAllLazyAll();

    FootWear findWithAllLazyById(Long id);

}
