package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.OuterWear;

public class OuterWearRMQ {

    private String operation;

    private OuterWear outerWear;

    public OuterWearRMQ() {
    }

    public OuterWearRMQ(String operation, OuterWear outerWear) {
        this.operation = operation;
        this.outerWear = outerWear;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public OuterWear getOuterWear() {
        return outerWear;
    }

    public void setOuterWear(OuterWear outerWear) {
        this.outerWear = outerWear;
    }
}
