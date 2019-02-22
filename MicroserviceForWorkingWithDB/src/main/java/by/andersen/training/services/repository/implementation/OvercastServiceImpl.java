package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.Overcast;
import by.andersen.training.repositories.OvercastRepository;
import by.andersen.training.services.repository.interfaces.OvercastService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        List<Overcast> footWears = new ArrayList<>();
        for(Overcast footWear : overcastRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
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

}
