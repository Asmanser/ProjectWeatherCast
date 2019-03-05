package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.WeatherClothing;
import by.andersen.training.weathercast.repositories.WeatherClothingRepository;
import by.andersen.training.weathercast.services.repository.interfaces.WeatherClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
        List<WeatherClothing> weatherClothings = new ArrayList<>();
        for(WeatherClothing weatherClothing : ((CrudRepository<WeatherClothing, Long>)weatherClothingRepository).findAll()) {
            weatherClothings.add(weatherClothing);
        }
        return weatherClothings;
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
        return weatherClothingRepository.findLazyById(aLong);
    }

    @Override
    public List<WeatherClothing> findWithAllLazyAll() {
        List<WeatherClothing> weatherClothings = new ArrayList<>();
        for(WeatherClothing weatherClothing : weatherClothingRepository.findAll()) {
            weatherClothings.add(weatherClothing);
        }
        return weatherClothings;
    }
}
