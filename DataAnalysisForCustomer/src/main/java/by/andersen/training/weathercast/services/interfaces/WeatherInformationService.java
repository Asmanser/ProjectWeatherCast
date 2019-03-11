package by.andersen.training.weathercast.services.interfaces;

import by.andersen.training.weathercast.models.WeatherInformation;

import java.util.Date;

public interface WeatherInformationService {

    public WeatherInformation getByDate(Date date, String login) throws InterruptedException;


    public WeatherInformation getByDateAndCity(java.sql.Date date, int city) throws InterruptedException;
}
