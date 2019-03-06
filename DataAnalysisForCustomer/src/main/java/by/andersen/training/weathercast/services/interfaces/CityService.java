package by.andersen.training.weathercast.services.interfaces;

import by.andersen.training.weathercast.models.City;

import java.util.List;

public interface CityService {

    public List<City> getAllCity() throws InterruptedException;

}
