package by.andersen.training.weathercast.models.rabbitmq;

import by.andersen.training.weathercast.models.UnderWear;

public class UnderWearRMQ {

    private String operation;

    private UnderWear underWear;

    public UnderWearRMQ() {
    }

    public UnderWearRMQ(String operation, UnderWear underWear) {
        this.operation = operation;
        this.underWear = underWear;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UnderWear getUnderWear() {
        return underWear;
    }

    public void setUnderWear(UnderWear underWear) {
        this.underWear = underWear;
    }
}
