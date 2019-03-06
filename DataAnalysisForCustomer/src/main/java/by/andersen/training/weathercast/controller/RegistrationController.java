package by.andersen.training.weathercast.controller;

import by.andersen.training.weathercast.model.User;
import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.PersonalInformation;
import by.andersen.training.weathercast.models.Role;
import by.andersen.training.weathercast.models.rabbitmq.CityRMQ;
import by.andersen.training.weathercast.models.rabbitmq.RoleRMQ;
import by.andersen.training.weathercast.models.rabbitmq.UserRMQ;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.CityService;
import by.andersen.training.weathercast.services.interfaces.RoleService;
import by.andersen.training.weathercast.services.interfaces.SecurityService;
import by.andersen.training.weathercast.services.interfaces.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Gson gson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private RoleService roleService;

    private List<City> cities;

    private List<Role> roles;

    /*public RegistrationController(BCryptPasswordEncoder bCryptPasswordEncoder, Gson gson,
                                  SecurityService securityService, UserService userService,
                                  CityService cityService, RoleService roleService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.gson = gson;
        this.securityService = securityService;
        this.userService = userService;
        this.cityService = cityService;
        this.roleService = roleService;
    }*/

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(Model model, HttpServletResponse response) throws InterruptedException {
        User user = new User();
        user.setPersonalInformation(new PersonalInformation());
        user.getPersonalInformation().setCity(new City());
        model.addAttribute("userForm", user);

        this.cities = cityService.getAllCity();

        this.roles = roleService.getAllRole();
        response.setCharacterEncoding("UTF-8");
        model.addAttribute("cities",cities);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, Model model,
                               HttpServletRequest request,HttpServletResponse response) throws InterruptedException, UnsupportedEncodingException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding("UTF-8");
        by.andersen.training.weathercast.models.User findUser = userService.getByLogin(userForm.getLogin());

        try {

            if (findUser != null) {
                model.addAttribute("error", "Username already exists.");
                return "registration";
            }
            if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
                model.addAttribute("error", "Passwords do not match.");
                return "registration";
            }
        }
        finally {
            model.addAttribute("cities",cities);
        }

        userForm.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        userService.addInformationNewUser(userForm, this.cities, this.roles);
        by.andersen.training.weathercast.models.User user = new by.andersen.training.weathercast.models.User();
        user.setLogin(userForm.getLogin());
        user.setPassword(userForm.getPassword());
        user.setPersonalInformation(userForm.getPersonalInformation());
        user.setRoles(userForm.getRoles());

        boolean answer = userService.save(user);
        if (!answer) {
            model.addAttribute("error", "The server can't add new user.");
            model.addAttribute("cities",cities);
            userForm.setPassword(userForm.getConfirmPassword());
            return "registration";
        }
        securityService.autoLogin(userForm.getLogin(), userForm.getPassword());

        return "redirect:/";
    }

}
