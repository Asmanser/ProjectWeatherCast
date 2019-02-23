package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.WeatherCondition;
import by.andersen.training.repositories.WeatherConditionRepository;
import by.andersen.training.services.repository.interfaces.WeatherConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherConditionServiceImpl implements WeatherConditionService {

    @Autowired
    private WeatherConditionRepository weatherConditionRepository;

    @Override
    public void save(WeatherCondition weatherCondition) {
        weatherConditionRepository.save(weatherCondition);
    }

    @Override
    public void saveAll(List<WeatherCondition> weatherConditions) {
        weatherConditionRepository.saveAll(weatherConditions);
    }

    @Override
    public WeatherCondition findById(Long id) {
        return weatherConditionRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return weatherConditionRepository.existsById(id);
    }

    @Override
    public List<WeatherCondition> findAll() {
        List<WeatherCondition> footWears = new ArrayList<>();
        for(WeatherCondition footWear : weatherConditionRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return weatherConditionRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        weatherConditionRepository.deleteById(id);
    }

    @Override
    public void delete(WeatherCondition weatherCondition) {
        weatherConditionRepository.delete(weatherCondition);
    }

    @Override
    public void deleteAll() {
        weatherConditionRepository.deleteAll();
    }

    @Override
    public Iterable<WeatherCondition> findWithAllLazyAll() {
        List<WeatherCondition> footWears = new ArrayList<>();
        for(WeatherCondition footWear : weatherConditionRepository.findWithAllLazyAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public WeatherCondition findWithAllLazyById(Long id) {
        return weatherConditionRepository.findWithAllLazyById(id).get();
    }
}
