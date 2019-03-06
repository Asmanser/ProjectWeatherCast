package by.andersen.training.weathercast.services.interfaces;

import by.andersen.training.weathercast.models.WeatherInformation;

import java.util.Date;

public interface WeatherInformationService {

    public WeatherInformation getByDate(Date date);

}
