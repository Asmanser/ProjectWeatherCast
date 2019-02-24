package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.Cap;

public class CapRMQ {

    private String operation;

    private Cap cap;

    public CapRMQ() {
    }

    public CapRMQ(String operation, Cap cap) {
        this.operation = operation;
        this.cap = cap;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Cap getCap() {
        return cap;
    }

    public void setCap(Cap cap) {
        this.cap = cap;
    }
}
