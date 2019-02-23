package by.andersen.training;

import by.andersen.training.config.AppConfig;
import by.andersen.training.models.Accessory;
import by.andersen.training.models.Cap;
import by.andersen.training.models.City;
import by.andersen.training.models.Country;
import by.andersen.training.models.FootWear;
import by.andersen.training.models.Image;
import by.andersen.training.models.OuterWear;
import by.andersen.training.models.PersonalInformation;
import by.andersen.training.models.Role;
import by.andersen.training.models.UnderWear;
import by.andersen.training.models.User;
import by.andersen.training.models.WeatherClothing;
import by.andersen.training.models.WeatherInformation;
import by.andersen.training.services.repository.implementation.UserServiceImpl;
import by.andersen.training.services.repository.implementation.WeatherClothingServiceImpl;
import by.andersen.training.services.repository.interfaces.CityService;
import by.andersen.training.services.repository.interfaces.UserService;
import by.andersen.training.services.repository.interfaces.WeatherClothingService;
import by.andersen.training.services.repository.interfaces.WeatherInformationService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WeatherInformationService weatherInformationService = context.getBean(WeatherInformationService.class);
        /*CityService cityService = context.getBean(CityService.class);
        City city = cityService.findById(8L);
        System.out.println(city.getCountry().getCountryName());*/
        //WeatherInformation weatherInformation = new WeatherInformation();
        UserService userRepository = context.getBean(UserService.class);
        WeatherClothingService weatherClothingService = context.getBean(WeatherClothingService.class);
        WeatherClothing withAllLazyById = weatherClothingService.findWithAllLazyById(2L);
        System.out.println(withAllLazyById);
        /*WeatherClothing weatherClothing = new WeatherClothing();
        Accessory accessory = new Accessory();
        accessory.setNameAccessory("Umbrella");
        Image image1 = new Image();
        image1.setNameImage("1");
        image1.setPathImage("2");
        accessory.setImage(image1);
        weatherClothing.setAccessory(accessory);
        OuterWear outerWear = new OuterWear();
        outerWear.setNameOuterWear("Blouse");
        Image image2 = new Image();
        image2.setNameImage("3");
        image2.setPathImage("4");
        outerWear.setImage(image2);
        weatherClothing.setOuterWear(outerWear);
        UnderWear underWear = new UnderWear();
        underWear.setNameUnderWear("Pants");
        Image image3 = new Image();
        image3.setNameImage("5");
        image3.setPathImage("6");
        underWear.setImage(image3);
        weatherClothing.setUnderWear(underWear);
        FootWear footWear = new FootWear();
        footWear.setNameFootWear("Sneakers");
        Image image4 = new Image();
        image4.setNameImage("7");
        image4.setPathImage("8");
        footWear.setImage(image4);
        weatherClothing.setFootWear(footWear);
        Cap cap = new Cap();
        cap.setNameCap("Cap");
        Image image5 = new Image();
        image5.setNameImage("9");
        image5.setPathImage("10");
        cap.setImage(image5);
        weatherClothing.setCap(cap);
        weatherClothingService.save(weatherClothing);*/
        /*Role role = new Role();
        role.setRoleName("Admin");
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        Country country = new Country();
        country.setCountryName("Belarus");
        City city = new City();
        city.setCityName("Gomel");
        city.setCountry(country);
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName("Alex");
        personalInformation.setSurname("Kazimov");
        personalInformation.setPatronymic("Ruslanovich");
        personalInformation.setAge(20);
        personalInformation.setEmail("bybirmybir@gmail.com");
        personalInformation.setCity(city);
        user.setPersonalInformation(personalInformation);
        userRepository.save(user);*/
    }

}
