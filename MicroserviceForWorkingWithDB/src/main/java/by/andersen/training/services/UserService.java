package by.andersen.training.services;

import by.andersen.training.models.User;
import by.andersen.training.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired(required=true)
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}
