package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.OuterWear;

import java.util.List;

public interface OuterWearService extends CrudService<OuterWear, Long> {

    List<OuterWear> findWithAllLazyAll();

    OuterWear findWithAllLazyById(Long id);

}
