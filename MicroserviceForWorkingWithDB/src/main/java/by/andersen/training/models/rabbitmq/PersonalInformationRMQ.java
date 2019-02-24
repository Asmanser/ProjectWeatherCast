package by.andersen.training.models.rabbitmq;

import by.andersen.training.models.PersonalInformation;

public class PersonalInformationRMQ {

    private String operation;

    private PersonalInformation personalInformation;

    public PersonalInformationRMQ() {
    }

    public PersonalInformationRMQ(String operation, PersonalInformation personalInformation) {
        this.operation = operation;
        this.personalInformation = personalInformation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }
}
