package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.UnderWear;

import java.util.List;

public interface UnderWearService extends CrudService<UnderWear, Long> {

    List<UnderWear> findWithAllLazyAll();

    UnderWear findWithAllLazyById(Long id);

}
