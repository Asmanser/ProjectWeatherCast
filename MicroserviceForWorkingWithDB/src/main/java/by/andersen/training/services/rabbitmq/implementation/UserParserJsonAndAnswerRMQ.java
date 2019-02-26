package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.Role;
import by.andersen.training.models.User;
import by.andersen.training.models.rabbitmq.UserRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private UserService userService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        UserRMQ userRMQ = gson.fromJson(json, UserRMQ.class);
        return doOperation(userRMQ.getOperation(),userRMQ.getUser());
    }

    private String doOperation(String operation, User user) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                userService.save(user);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                userService.deleteById(user.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                userService.save(user);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<User> users = userService.findWithAllLazyAll();
                for(User currentUser : users) {
                    for(Role role : currentUser.getRoles()) {
                        role.setUsers(null);
                    }
                    currentUser.getPersonalInformation().getCity().setPersonalInformations(null);
                    currentUser.getPersonalInformation().getCity().setWeatherInformations(null);
                    currentUser.getPersonalInformation().getCity().getCountry().setCities(null);
                }
                return gson.toJson(users);
            }
            case "GETBYID": {
                User findUser = userService.findById(user.getId());
                findUser.setRoles(null);
                findUser.setPersonalInformation(null);
                return gson.toJson(findUser);
            }
            case "GETBYLAZYID": {
                User findUser = userService.findWithAllLazyById(user.getId());
                for(Role role : findUser.getRoles()) {
                    role.setUsers(null);
                }
                findUser.getPersonalInformation().getCity().setPersonalInformations(null);
                findUser.getPersonalInformation().getCity().setWeatherInformations(null);
                findUser.getPersonalInformation().getCity().getCountry().setCities(null);
                return gson.toJson(findUser);
            }
            case "GETALL": {
                List<User> users = userService.findAll();
                for(User currentUser : users) {
                    currentUser.setRoles(null);
                    currentUser.setPersonalInformation(null);
                }
                return gson.toJson(users);
            }
        }
        return answer;
    }

}
