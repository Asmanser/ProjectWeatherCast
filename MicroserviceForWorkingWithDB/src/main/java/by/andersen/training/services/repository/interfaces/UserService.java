package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    List<User> findWithAllLazyAll();

    User findWithAllLazyById(Long id);

}
