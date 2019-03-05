package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.repositories.CityRepository;
import by.andersen.training.weathercast.services.repository.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
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
    @Transactional
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
        for(City city : ((CrudRepository<City, Long>)cityRepository).findAll()) {
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

    @Override
    public List<City> findWithAllLazyAll() {
        List<City> cities = new ArrayList<>();
        for(City city : cityRepository.findAll()) {
            cities.add(city);
        }
        return cities;
    }

    @Override
    public City findWithAllLazyById(Long id) {
        return cityRepository.findLazyById(id).get();
    }
}
