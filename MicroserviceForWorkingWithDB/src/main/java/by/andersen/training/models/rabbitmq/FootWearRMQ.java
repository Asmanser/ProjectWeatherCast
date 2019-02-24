package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.FootWear;

public class FootWearRMQ {

    private String operation;

    private FootWear footWear;

    public FootWearRMQ(String operation, FootWear footWear) {
        this.operation = operation;
        this.footWear = footWear;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public FootWear getFootWear() {
        return footWear;
    }

    public void setFootWear(FootWear footWear) {
        this.footWear = footWear;
    }
}
