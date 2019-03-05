package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.Overcast;
import by.andersen.training.weathercast.repositories.OvercastRepository;
import by.andersen.training.weathercast.services.repository.interfaces.OvercastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OvercastServiceImpl implements OvercastService {

    @Autowired
    private OvercastRepository overcastRepository;

    @Override
    public void save(Overcast overcast) {
        overcastRepository.save(overcast);
    }

    @Override
    public void saveAll(List<Overcast> overcasts) {
        overcastRepository.saveAll(overcasts);
    }

    @Override
    public Overcast findById(Long id) {
        return overcastRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return overcastRepository.existsById(id);
    }

    @Override
    public List<Overcast> findAll() {
        List<Overcast> overcasts = new ArrayList<>();
        for(Overcast overcast : ((CrudRepository<Overcast, Long>)overcastRepository).findAll()) {
            overcasts.add(overcast);
        }
        return overcasts;
    }

    @Override
    public long count() {
        return overcastRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        overcastRepository.deleteById(id);
    }

    @Override
    public void delete(Overcast overcast) {
        overcastRepository.delete(overcast);
    }

    @Override
    public void deleteAll() {
        overcastRepository.deleteAll();
    }

    @Override
    public List<Overcast> findWithAllLazyAll() {
        List<Overcast> overcasts = new ArrayList<>();
        for(Overcast overcast : overcastRepository.findAll()) {
            overcasts.add(overcast);
        }
        return overcasts;
    }

    @Override
    public Overcast findWithAllLazyById(Long id) {
        return overcastRepository.findLazyById(id).get();
    }
}
