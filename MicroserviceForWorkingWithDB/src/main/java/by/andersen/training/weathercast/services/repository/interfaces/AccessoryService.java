package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.Accessory;

import java.util.List;

public interface AccessoryService extends CrudService<Accessory, Long> {

    List<Accessory> findWithAllLazyAll();

    Accessory findWithAllLazyById(Long id);

}
