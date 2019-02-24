package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.DirectionWind;

public class DirectionWindRMQ {

    private String operation;

    private DirectionWind directionWind;

    public DirectionWindRMQ() {
    }

    public DirectionWindRMQ(String operation, DirectionWind directionWind) {
        this.operation = operation;
        this.directionWind = directionWind;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public DirectionWind getDirectionWind() {
        return directionWind;
    }

    public void setDirectionWind(DirectionWind directionWind) {
        this.directionWind = directionWind;
    }
}
