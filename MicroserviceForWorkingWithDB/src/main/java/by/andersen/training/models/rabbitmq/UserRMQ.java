package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.User;

public class UserRMQ {

    private String operation;

    private User user;

    public UserRMQ() {
    }

    public UserRMQ(String operation, User user) {
        this.operation = operation;
        this.user = user;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
