package by.andersen.training.weathercast.controller;

import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.rmq.SendMessageAndAcceptResponseRMQ;
import by.andersen.training.weathercast.services.interfaces.CityService;
import by.andersen.training.weathercast.services.interfaces.UserService;
import by.andersen.training.weathercast.services.interfaces.WeatherInformationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private WeatherInformationService weatherInformationService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/")
    public String index(Model model, HttpServletResponse response) throws InterruptedException {
        response.setCharacterEncoding("UTF-8");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        WeatherInformation weatherInformation = weatherInformationService.getByDate(Date.valueOf(LocalDate.now()),name);
        model.addAttribute("weatherInformation",weatherInformation);
        model.addAttribute("perinfo",userService.getByLogin(name).getPersonalInformation());
        model.addAttribute("cities",cityService.getAllCity());
        return "index";
    }

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index(@RequestParam int city, @RequestParam Date date, Model model, HttpServletResponse response) throws InterruptedException {
        response.setCharacterEncoding("UTF-8");
        WeatherInformation weatherInformation = weatherInformationService.getByDateAndCity(date, city);
        if(weatherInformation!=null)
            return gson.toJson(weatherInformation);
        else
            return "false";
    }

}
