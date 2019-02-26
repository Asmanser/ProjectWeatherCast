package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.Accessory;

import java.util.List;

public interface AccessoryService extends CrudService<Accessory, Long> {

    List<Accessory> findWithAllLazyAll();

    Accessory findWithAllLazyById(Long id);

}
