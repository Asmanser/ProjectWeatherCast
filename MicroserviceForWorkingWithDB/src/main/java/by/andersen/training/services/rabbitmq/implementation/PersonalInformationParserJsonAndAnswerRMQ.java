package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.PersonalInformation;
import by.andersen.training.models.rabbitmq.PersonalInformationRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.PersonalInformationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonalInformationParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private PersonalInformationService personalInformationService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        PersonalInformationRMQ personalInformationRMQ = gson.fromJson(json, PersonalInformationRMQ.class);
        return doOperation(personalInformationRMQ.getOperation(),personalInformationRMQ.getPersonalInformation());
    }

    private String doOperation(String operation, PersonalInformation personalInformation) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                personalInformationService.save(personalInformation);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                personalInformationService.deleteById(personalInformation.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                personalInformationService.save(personalInformation);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<PersonalInformation> personalInformations = personalInformationService.findWithAllLazyAll();
                for(PersonalInformation currentPersonalInformation : personalInformations) {
                    currentPersonalInformation.getCity().setPersonalInformations(null);
                    currentPersonalInformation.getCity().setWeatherInformations(null);
                    currentPersonalInformation.getCity().getCountry().setCities(null);
                }
                return gson.toJson(personalInformations);
            }
            case "GETBYID": {
                PersonalInformation findPersonalInformation = personalInformationService.findById(personalInformation.getId());
                findPersonalInformation.setCity(null);
                return gson.toJson(findPersonalInformation);
            }
            case "GETBYLAZYID": {
                PersonalInformation findPerosnlaInformation = personalInformationService.findWithAllLazyById(personalInformation.getId());
                findPerosnlaInformation.getCity().setWeatherInformations(null);
                findPerosnlaInformation.getCity().setPersonalInformations(null);
                findPerosnlaInformation.getCity().getCountry().setCities(null);
                return gson.toJson(findPerosnlaInformation);
            }
            case "GETALL": {
                List<PersonalInformation> personalInformations = personalInformationService.findAll();
                for(PersonalInformation currentPersonalInformations : personalInformations) {
                    currentPersonalInformations.setCity(null);
                }
                return gson.toJson(personalInformations);
            }
        }
        return answer;
    }

}
