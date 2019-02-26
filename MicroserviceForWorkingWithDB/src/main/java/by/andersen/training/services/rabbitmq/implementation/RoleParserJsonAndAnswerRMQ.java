package by.andersen.training.services.rabbitmq.implementation;

import by.andersen.training.models.Role;
import by.andersen.training.models.rabbitmq.RoleRMQ;
import by.andersen.training.services.rabbitmq.interfaces.ParserJsonAndAnswerRMQ;
import by.andersen.training.services.repository.interfaces.RoleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private RoleService roleService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        RoleRMQ roleRMQ = gson.fromJson(json, RoleRMQ.class);
        return doOperation(roleRMQ.getOperation(),roleRMQ.getRole());
    }

    private String doOperation(String operation, Role role) {
        String answer = "";
        switch(operation) {
            case "SAVE": {
                roleService.save(role);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                roleService.deleteById(role.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                roleService.save(role);
                answer = "true";
                return answer;
            }
            case "GETBYID": {
                Role findRole = roleService.findById(role.getId());
                findRole.setUsers(null);
                return gson.toJson(findRole);
            }
            case "GETALL": {
                List<Role> roles = roleService.findAll();
                for(Role currentRole : roles) {
                    currentRole.setUsers(null);
                }
                return gson.toJson(roles);
            }
        }
        return answer;
    }

}
