package by.andersen.training.weathercast.services.repository.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.repositories.WeatherInformationRepository;
import by.andersen.training.weathercast.services.repository.interfaces.WeatherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherInformationServiceImpl implements WeatherInformationService {

    @Autowired
    private WeatherInformationRepository weatherInformationRepository;

    @Override
    public void save(WeatherInformation weatherInformation) {
        weatherInformationRepository.save(weatherInformation);
    }

    @Override
    public void saveAll(List<WeatherInformation> weatherInformations) {
        weatherInformationRepository.saveAll(weatherInformations);
    }

    @Override
    public WeatherInformation findById(Long id) {
        return weatherInformationRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return weatherInformationRepository.existsById(id);
    }

    @Override
    public List<WeatherInformation> findAll() {
        List<WeatherInformation> weatherInformations = new ArrayList<>();
        for(WeatherInformation weatherInformation : ((CrudRepository<WeatherInformation, Long>)weatherInformationRepository).findAll()) {
            weatherInformations.add(weatherInformation);
        }
        return weatherInformations;
    }

    @Override
    public long count() {
        return weatherInformationRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        weatherInformationRepository.deleteById(id);
    }

    @Override
    public void delete(WeatherInformation weatherInformation) {
        weatherInformationRepository.delete(weatherInformation);
    }

    @Override
    public void deleteAll() {
        weatherInformationRepository.deleteAll();
    }

    @Override
    public List<WeatherInformation> findWithAllLazyAll() {
        List<WeatherInformation> weatherInformations = new ArrayList<>();
        for(WeatherInformation weatherInformation : weatherInformationRepository.findAll()) {
            weatherInformations.add(weatherInformation);
        }
        return weatherInformations;
    }

    @Override
    public WeatherInformation findWithAllLazyById(Long id) {
        return weatherInformationRepository.findLazyById(id).get();
    }

    @Override
    public List<WeatherInformation> findLazyByDate(Date date) {
        List<WeatherInformation> weatherInformations = new ArrayList<>();
        for(WeatherInformation weatherInformation: weatherInformationRepository.findLazyByDate(date)) {
            weatherInformations.add(weatherInformation);
        }
        return weatherInformations;
    }

    @Override
    public WeatherInformation findLazyByDateAndCity(Date date, City city) {
        Optional<WeatherInformation> lazyByDateAndCity = weatherInformationRepository.findLazyByDateAndCity(date, city);
        if(lazyByDateAndCity.isPresent()) {
            return lazyByDateAndCity.get();
        }
        return null;
    }
}
