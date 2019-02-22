package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.DirectionWind;
import by.andersen.training.repositories.DirectionWindRepository;
import by.andersen.training.services.repository.interfaces.DirectionWindService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DirectionWindServiceImpl implements DirectionWindService {

    @Autowired
    private DirectionWindRepository directionWindRepository;

    @Override
    public void save(DirectionWind directionWind) {
        directionWindRepository.save(directionWind);
    }

    @Override
    public void saveAll(List<DirectionWind> directionWinds) {
        directionWindRepository.saveAll(directionWinds);
    }

    @Override
    public DirectionWind findById(Long id) {
        return directionWindRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return directionWindRepository.existsById(id);
    }

    @Override
    public List<DirectionWind> findAll() {
        List<DirectionWind> directionWinds = new ArrayList<>();
        for(DirectionWind directionWind : directionWindRepository.findAll()) {
            directionWinds.add(directionWind);
        }
        return directionWinds;
    }

    @Override
    public long count() {
        return directionWindRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        directionWindRepository.deleteById(id);
    }

    @Override
    public void delete(DirectionWind directionWind) {
        directionWindRepository.delete(directionWind);
    }

    @Override
    public void deleteAll() {
        directionWindRepository.deleteAll();
    }
}
