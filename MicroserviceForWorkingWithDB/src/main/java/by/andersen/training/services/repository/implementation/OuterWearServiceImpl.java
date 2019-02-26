package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.OuterWear;
import by.andersen.training.repositories.OuterWearRepository;
import by.andersen.training.services.repository.interfaces.OuterWearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OuterWearServiceImpl implements OuterWearService {

    @Autowired
    private OuterWearRepository outerWearRepository;

    @Override
    public void save(OuterWear outerWear) {
        outerWearRepository.save(outerWear);
    }

    @Override
    public void saveAll(List<OuterWear> outerWears) {
        outerWearRepository.saveAll(outerWears);
    }

    @Override
    public OuterWear findById(Long id) {
        return outerWearRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return outerWearRepository.existsById(id);
    }

    @Override
    public List<OuterWear> findAll() {
        List<OuterWear> outerWears = new ArrayList<>();
        for(OuterWear outerWear : ((CrudRepository<OuterWear, Long>)outerWearRepository).findAll()) {
            outerWears.add(outerWear);
        }
        return outerWears;
    }

    @Override
    public long count() {
        return outerWearRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        outerWearRepository.deleteById(id);
    }

    @Override
    public void delete(OuterWear outerWear) {
        outerWearRepository.delete(outerWear);
    }

    @Override
    public void deleteAll() {
        outerWearRepository.deleteAll();
    }

    @Override
    public List<OuterWear> findWithAllLazyAll() {
        List<OuterWear> outerWears = new ArrayList<>();
        for(OuterWear outerWear : outerWearRepository.findAll()) {
            outerWears.add(outerWear);
        }
        return outerWears;
    }

    @Override
    public OuterWear findWithAllLazyById(Long id) {
        return outerWearRepository.findLazyById(id).get();
    }
}
