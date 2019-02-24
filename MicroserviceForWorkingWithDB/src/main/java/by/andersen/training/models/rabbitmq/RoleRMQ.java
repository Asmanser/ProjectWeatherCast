package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.Role;

public class RoleRMQ {

    private String operation;

    private Role role;

    public RoleRMQ() {
    }

    public RoleRMQ(String operation, Role role) {
        this.operation = operation;
        this.role = role;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
