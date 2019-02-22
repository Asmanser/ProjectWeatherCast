package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.City;
import by.andersen.training.repositories.CityRepository;
import by.andersen.training.services.repository.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void saveAll(List<City> cities) {
        cityRepository.saveAll(cities);
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return cityRepository.existsById(id);
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        for(City city : cityRepository.findAll()) {
            cities.add(city);
        }
        return cities;
    }

    @Override
    public long count() {
        return cityRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
