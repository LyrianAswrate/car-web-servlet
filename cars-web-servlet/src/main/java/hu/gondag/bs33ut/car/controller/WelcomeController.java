package hu.gondag.bs33ut.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.gondag.bs33ut.car.security.service.CarModelService;
import hu.gondag.bs33ut.car.security.service.CarService;

@Controller
public class WelcomeController {

    public static final String WELCOME = "welcome";

    @Autowired
    private CarService carService;

    @Autowired
    private CarModelService carModelService;

    @RequestMapping(path = "/welcome", method = { RequestMethod.GET, RequestMethod.POST })
    public String welcome(Model model) {
        model.addAttribute("cars", carService.getAll());
        model.addAttribute("carmodels", carModelService.getAll());
        return WelcomeController.WELCOME;
    }

}
