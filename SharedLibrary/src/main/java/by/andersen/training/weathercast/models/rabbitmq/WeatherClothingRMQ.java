package by.andersen.training.weathercast.models.rabbitmq;

import by.andersen.training.weathercast.models.WeatherClothing;

public class WeatherClothingRMQ {

    private String operation;

    private WeatherClothing weatherClothing;

    public WeatherClothingRMQ() {
    }

    public WeatherClothingRMQ(String operation, WeatherClothing weatherClothing) {
        this.operation = operation;
        this.weatherClothing = weatherClothing;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public WeatherClothing getWeatherClothing() {
        return weatherClothing;
    }

    public void setWeatherClothing(WeatherClothing weatherClothing) {
        this.weatherClothing = weatherClothing;
    }
}
