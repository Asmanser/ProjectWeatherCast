package by.andersen.training.services.repository.interfaces;

import by.andersen.training.models.User;

public interface UserService extends CrudService<User, Long> {

    Iterable<User> findWithAllLazyAll();

    User findWithAllLazyById(Long id);

}
