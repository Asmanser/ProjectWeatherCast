package by.andersen.training.services.repository.implementation;

import by.andersen.training.models.WeatherInformation;
import by.andersen.training.repositories.WeatherInformationRepository;
import by.andersen.training.services.repository.interfaces.WeatherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
