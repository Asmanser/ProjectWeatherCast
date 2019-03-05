package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.UnderWear;
import by.andersen.training.weathercast.repositories.UnderWearRepository;
import by.andersen.training.weathercast.services.repository.interfaces.UnderWearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnderWearServiceImpl implements UnderWearService {

    @Autowired
    private UnderWearRepository underWearRepository;

    @Override
    public void save(UnderWear underWear) {
        underWearRepository.save(underWear);
    }

    @Override
    public void saveAll(List<UnderWear> underWears) {
        underWearRepository.saveAll(underWears);
    }

    @Override
    public UnderWear findById(Long id) {
        return underWearRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return underWearRepository.existsById(id);
    }

    @Override
    public List<UnderWear> findAll() {
        List<UnderWear> underWears = new ArrayList<>();
        for(UnderWear underWear : ((CrudRepository<UnderWear, Long>)underWearRepository).findAll()) {
            underWears.add(underWear);
        }
        return underWears;
    }

    @Override
    public long count() {
        return underWearRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        underWearRepository.deleteById(id);
    }

    @Override
    public void delete(UnderWear underWear) {
        underWearRepository.delete(underWear);
    }

    @Override
    public void deleteAll() {
        underWearRepository.deleteAll();
    }

    @Override
    public List<UnderWear> findWithAllLazyAll() {
        List<UnderWear> underWears = new ArrayList<>();
        for(UnderWear underWear : underWearRepository.findAll()) {
            underWears.add(underWear);
        }
        return underWears;
    }

    @Override
    public UnderWear findWithAllLazyById(Long id) {
        return underWearRepository.findLazyById(id).get();
    }
}
