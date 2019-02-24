package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.Overcast;

public class OvercastRMQ {

    private String operation;

    private Overcast overcast;

    public OvercastRMQ() {
    }

    public OvercastRMQ(String operation, Overcast overcast) {
        this.operation = operation;
        this.overcast = overcast;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Overcast getOvercast() {
        return overcast;
    }

    public void setOvercast(Overcast overcast) {
        this.overcast = overcast;
    }
}
