package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.FootWear;
import by.andersen.training.repositories.FootWearRepository;
import by.andersen.training.services.repository.interfaces.FootWearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FootWearServiceImpl implements FootWearService {

    @Autowired
    private FootWearRepository footWearRepository;

    @Override
    public void save(FootWear footWear) {
        footWearRepository.save(footWear);
    }

    @Override
    public void saveAll(List<FootWear> footWears) {
        footWearRepository.saveAll(footWears);
    }

    @Override
    public FootWear findById(Long id) {
        return footWearRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return footWearRepository.existsById(id);
    }

    @Override
    public List<FootWear> findAll() {
        List<FootWear> footWears = new ArrayList<>();
        for(FootWear footWear : footWearRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return footWearRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        footWearRepository.deleteById(id);
    }

    @Override
    public void delete(FootWear footWear) {
        footWearRepository.delete(footWear);
    }

    @Override
    public void deleteAll() {
        footWearRepository.deleteAll();
    }

    @Override
    public Iterable<FootWear> findWithAllLazyAll() {
        List<FootWear> footWears = new ArrayList<>();
        for(FootWear footWear : footWearRepository.findWithAllLazyAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public FootWear findWithAllLazyById(Long id) {
        return footWearRepository.findWithAllLazyById(id).get();
    }
}
