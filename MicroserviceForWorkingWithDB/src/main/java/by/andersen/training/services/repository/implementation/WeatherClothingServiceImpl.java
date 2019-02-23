package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.WeatherClothing;
import by.andersen.training.repositories.WeatherClothingRepository;
import by.andersen.training.services.repository.interfaces.WeatherClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherClothingServiceImpl implements WeatherClothingService {

    @Autowired
    private WeatherClothingRepository weatherClothingRepository;

    @Override
    public void save(WeatherClothing weatherClothing) {
        weatherClothingRepository.save(weatherClothing);
    }

    @Override
    public void saveAll(List<WeatherClothing> weatherClothings) {
        weatherClothingRepository.saveAll(weatherClothings);
    }

    @Override
    public WeatherClothing findById(Long id) {
        return weatherClothingRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return weatherClothingRepository.existsById(id);
    }

    @Override
    public List<WeatherClothing> findAll() {
        List<WeatherClothing> footWears = new ArrayList<>();
        for(WeatherClothing footWear : weatherClothingRepository.findAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }

    @Override
    public long count() {
        return weatherClothingRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        weatherClothingRepository.deleteById(id);
    }

    @Override
    public void delete(WeatherClothing weatherClothing) {
        weatherClothingRepository.delete(weatherClothing);
    }

    @Override
    public void deleteAll() {
        weatherClothingRepository.deleteAll();
    }

    @Override
    public WeatherClothing findWithAllLazyById(Long aLong) {
        return weatherClothingRepository.findWithAllLazyById(aLong);
    }

    @Override
    public Iterable<WeatherClothing> findWithAllLazyAll() {
        List<WeatherClothing> footWears = new ArrayList<>();
        for(WeatherClothing footWear : weatherClothingRepository.findWithAllLazyAll()) {
            footWears.add(footWear);
        }
        return footWears;
    }
}
