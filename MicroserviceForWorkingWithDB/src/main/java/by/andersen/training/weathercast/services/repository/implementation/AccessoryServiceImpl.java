package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.Accessory;
import by.andersen.training.weathercast.repositories.AccessoryRepository;
import by.andersen.training.weathercast.services.repository.interfaces.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Override
    public void save(Accessory accessory) {
        accessoryRepository.save(accessory);
    }

    @Override
    public void saveAll(List<Accessory> accessories) {
        accessoryRepository.saveAll(accessories);
    }

    @Override
    public Accessory findById(Long id) {
        return accessoryRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return accessoryRepository.existsById(id);
    }

    @Override
    public List<Accessory> findAll() {
        List<Accessory> accessories = new ArrayList<>();
        Iterable<Accessory> all = ((CrudRepository<Accessory, Long>)accessoryRepository).findAll();
        for(Accessory accessory : all) {
            accessories.add(accessory);
        }
        return accessories;
    }

    @Override
    public long count() {
        return accessoryRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        accessoryRepository.deleteById(id);
    }

    @Override
    public void delete(Accessory accessory) {
        accessoryRepository.delete(accessory);
    }

    @Override
    public void deleteAll() {
        accessoryRepository.deleteAll();
    }

    @Override
    public List<Accessory> findWithAllLazyAll() {
        List<Accessory> accessories = new ArrayList<>();
        Iterable<Accessory> all = accessoryRepository.findAll();
        for(Accessory accessory : all) {
            accessories.add(accessory);
        }
        return accessories;
    }

    @Override
    public Accessory findWithAllLazyById(Long id) {
        return accessoryRepository.findLazyById(id).get();
    }
}
