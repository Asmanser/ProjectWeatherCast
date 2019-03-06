package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.rabbitmq.RoleRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.RoleService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private String QUEUE_NAME = "role";

    private String GET_ALL = "GETALL";

    private SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ;

    private Gson gson;

    public RoleServiceImpl(SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ, Gson gson) {
        this.sendMessageAndAcceptResponseRMQ = sendMessageAndAcceptResponseRMQ;
        this.gson = gson;
    }

    @Override
    public List<Role> getAllRole() throws InterruptedException {
        List<Role> roles = new ArrayList<>();
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new RoleRMQ(GET_ALL,new Role())));
        Thread thread2 = new Thread(sendMessageAndAcceptResponseRMQ);
        thread2.start();
        thread2.join();
        Type jsonTypeRoles = new TypeToken<ArrayList<Role>>(){}.getType();
        roles = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), jsonTypeRoles);
        return roles;
    }
}
