package by.andersen.training.weathercast.services.repository.interfaces;

import by.andersen.training.weathercast.models.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    List<User> findWithAllLazyAll();

    User findWithAllLazyById(Long id);

    User findByLogin(String login);

}
