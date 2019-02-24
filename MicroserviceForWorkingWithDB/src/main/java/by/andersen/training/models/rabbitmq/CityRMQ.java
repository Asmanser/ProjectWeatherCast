package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.City;

public class CityRMQ {

    private String operation;

    private City city;

    public CityRMQ() {
    }

    public CityRMQ(String operation, City city) {
        this.operation = operation;
        this.city = city;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
