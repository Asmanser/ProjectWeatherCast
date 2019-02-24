package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.Country;

public class CountryRMQ {

    private String operation;

    private Country country;

    public CountryRMQ() {
    }

    public CountryRMQ(String operation, Country country) {
        this.operation = operation;
        this.country = country;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
