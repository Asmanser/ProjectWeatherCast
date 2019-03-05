package by.andersen.training.weathercast.models.rabbitmq;

import by.andersen.training.weathercast.models.Image;

public class ImageRMQ {

    private String operation;

    private Image image;

    public ImageRMQ() {
    }

    public ImageRMQ(String operation, Image image) {
        this.operation = operation;
        this.image = image;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
