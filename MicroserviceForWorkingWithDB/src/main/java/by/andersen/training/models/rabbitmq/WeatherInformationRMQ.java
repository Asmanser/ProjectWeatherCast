package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.WeatherInformation;

public class WeatherInformationRMQ {

    private String operation;

    private WeatherInformation weatherInformation;

    public WeatherInformationRMQ() {
    }

    public WeatherInformationRMQ(String operation, WeatherInformation weatherInformation) {
        this.operation = operation;
        this.weatherInformation = weatherInformation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public WeatherInformation getWeatherInformation() {
        return weatherInformation;
    }

    public void setWeatherInformation(WeatherInformation weatherInformation) {
        this.weatherInformation = weatherInformation;
    }
}
