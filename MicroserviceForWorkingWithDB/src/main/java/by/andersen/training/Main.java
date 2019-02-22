package by.andersen.training;

import by.andersen.training.config.AppConfig;
import by.andersen.training.models.City;
import by.andersen.training.models.Country;
import by.andersen.training.models.PersonalInformation;
import by.andersen.training.models.Role;
import by.andersen.training.models.User;
import by.andersen.training.services.repository.implementation.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserServiceImpl userRepository = context.getBean(UserServiceImpl.class);
        Role role = new Role();
        role.setRoleName("Admin");
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        Country country = new Country();
        country.setCountryName("Беларусь");
        City city = new City();
        city.setCityName("Гомель");
        city.setCountry(country);
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName("Александр");
        personalInformation.setSurname("Казимов");
        personalInformation.setPatronymic("Русланович");
        personalInformation.setAge(20);
        personalInformation.setEmail("bybirmybir@gmail.com");
        personalInformation.setCity(city);
        user.setPersonalInformation(personalInformation);
        userRepository.save(user);
    }

}
