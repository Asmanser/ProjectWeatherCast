package by.andersen.training.weathercast.controller;

import by.andersen.training.weathercast.models.User;
import by.andersen.training.weathercast.services.interfaces.SecurityService;
import by.andersen.training.weathercast.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) throws InterruptedException {
        List<User> users = userService.getAllLazy();
        model.addAttribute("users",users);
        return "admin";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    public String user(@RequestParam(required = false) int id, @RequestParam String _method) throws InterruptedException {
        if(_method.equals("delete")) {
            userService.delete(id);
        }
        return  "redirect:/admin";
    }

    @RequestMapping(value = "/admin/role", method = RequestMethod.POST)
    public String role(@RequestParam String _method, @RequestParam(required = false) String login) throws InterruptedException {
        if(_method.equals("post")) {
            userService.addRole(login);
        } else {
            if(_method.equals("delete")) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String name = auth.getName();
                userService.deleteRole(login);
                if(name.equals(login)) {
                    auth.setAuthenticated(false);
                    return "redirect:/";
                }
            }
        }
        return "redirect:/admin";
    }

}
