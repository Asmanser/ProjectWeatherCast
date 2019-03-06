package by.andersen.training.weathercast.services.interfaces;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.User;

import java.util.List;

public interface UserService {

    public User getByLogin(String login);

    public boolean save(User user) throws InterruptedException;

    public void addInformationNewUser(User user, List<City> cities, List<Role> roles);

    public List<User> getAllLazyUser() throws InterruptedException;

}
