package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.Accessory;

public interface AccessoryService extends CrudService<Accessory, Long> {

    Iterable<Accessory> findWithAllLazyAll();

    Accessory findWithAllLazyById(Long id);

}
