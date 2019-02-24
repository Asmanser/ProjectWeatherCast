package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.WeatherCondition;

public class WeatherConditionRMQ {

    private String operation;

    private WeatherCondition weatherCondition;

    public WeatherConditionRMQ() {
    }

    public WeatherConditionRMQ(String operation, WeatherCondition weatherCondition) {
        this.operation = operation;
        this.weatherCondition = weatherCondition;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
