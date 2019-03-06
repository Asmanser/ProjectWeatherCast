package by.andersen.training.weathercast.services.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.models.rabbitmq.UserRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.RoleService;
import by.andersen.training.weathercast.services.interfaces.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private String DEFAULT_ROLE_NEW_USER = "ROLE_USER";

    private String SAVE = "SAVE";

    private String GETBYLOGIN = "GETBYLOGIN";

    private String GET_ALL_WITH_LAZY = "GETALLWITHLAZY";

    private String DELETE = "DELETE";

    private String QUEUE_NAME = "user";

    private int ID_ROLE_ADMIN = 1;

    private SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ;

    private Gson gson;

    private RoleService roleService;

    public UserServiceImpl(SendMessageAndAcceptResponseRMQ sendMessageAndAcceptResponseRMQ, Gson gson, RoleService roleService) {
        this.sendMessageAndAcceptResponseRMQ = sendMessageAndAcceptResponseRMQ;
        this.gson = gson;
        this.roleService = roleService;
    }

    @Override
    public User getByLogin(String login) throws UsernameNotFoundException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        User sendUser = new User();
        sendUser.setLogin(login);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new UserRMQ(GETBYLOGIN,sendUser)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = null;
        if (!sendMessageAndAcceptResponseRMQ.getAnswer().equals("false")) {
            user = gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), User.class);
        }
        return user;
    }

    @Override
    public boolean save(User user) throws InterruptedException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new UserRMQ(SAVE,user)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        thread.join();
        return Boolean.valueOf(sendMessageAndAcceptResponseRMQ.getAnswer());
    }

    @Override
    public void addInformationNewUser(User user, List<City> cities, List<Role> roles) {
        for(City city : cities) {
            if(city.getId() == user.getPersonalInformation().getCity().getId()) {
                user.getPersonalInformation().setCity(city);
                break;
            }
        }
        for(Role role: roles) {
            if(role.getRoleName().equals(DEFAULT_ROLE_NEW_USER)) {
                List<Role> newRoles = new ArrayList<>();
                newRoles.add(role);
                user.setRoles(newRoles);
            }
        }
    }

    @Override
    public List<User> getAllLazy() throws InterruptedException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new UserRMQ(GET_ALL_WITH_LAZY,new User())));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        thread.join();

        Type jsonTypeUser = new TypeToken<ArrayList<User>>(){}.getType();
        return gson.fromJson(sendMessageAndAcceptResponseRMQ.getAnswer(), jsonTypeUser);
    }

    @Override
    public boolean delete(int id) throws InterruptedException {
        sendMessageAndAcceptResponseRMQ.setRPC_Queue_Name(QUEUE_NAME);
        User user = new User();
        user.setId((long)id);
        sendMessageAndAcceptResponseRMQ.setMessage(gson.toJson(new UserRMQ(DELETE,user)));
        Thread thread = new Thread(sendMessageAndAcceptResponseRMQ);
        thread.start();
        thread.join();
        return Boolean.valueOf(sendMessageAndAcceptResponseRMQ.getAnswer());
    }

    @Override
    public void addRole(String login) throws InterruptedException {
        User user = getByLogin(login);
        user.getRoles().add(roleService.getById(ID_ROLE_ADMIN));
        save(user);
    }

    @Override
    public void deleteRole(String login) throws InterruptedException {
        User user = getByLogin(login);
        Role role = roleService.getById(ID_ROLE_ADMIN);
        int position = 0;
        for(int i = 0; i < user.getRoles().size(); i++) {
            if(role.getRoleName().equals(user.getRoles().get(i).getRoleName())) {
                position = i;
            }
        }
        user.getRoles().remove(position);
        save(user);
    }
}
