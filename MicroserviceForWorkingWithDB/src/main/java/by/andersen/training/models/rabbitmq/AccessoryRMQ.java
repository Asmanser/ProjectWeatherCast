package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.Accessory;

public class AccessoryRMQ {

    private String operation;

    private Accessory accessory;

    public AccessoryRMQ(String operation, Accessory accessory) {
        this.operation = operation;
        this.accessory = accessory;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }
}
