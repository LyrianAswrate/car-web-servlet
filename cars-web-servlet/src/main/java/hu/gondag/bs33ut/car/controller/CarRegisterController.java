package hu.gondag.bs33ut.car.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.gondag.bs33ut.car.dto.CarEditModel;
import hu.gondag.bs33ut.car.security.service.CarModelService;
import hu.gondag.bs33ut.car.security.service.CarService;

@Controller
public class CarRegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(CarRegisterController.class);

    public static final String CARREGISTER = "carregister";

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarService carService;

    @GetMapping(path = { "/carregister", })
    public String register(Model model) {
        model.addAttribute("pageTitle", "Gépjármű Regisztrálása");
        model.addAttribute("carModels", carModelService.getAll());
        return CarRegisterController.CARREGISTER;
    }

    @PostMapping(path = { "/carRegisterEdit" })
    public String edit(Model model, @RequestParam Long carId) {
        model.addAttribute("pageTitle", "Gépjármű Szerkesztés");
        model.addAttribute("car", carService.getById(carId));
        model.addAttribute("carModels", carModelService.getAll());
        return CarRegisterController.CARREGISTER;
    }

    @PostMapping(path = { "/carregister" })
    public String save(Model model, @ModelAttribute CarEditModel car) {
        try {
            if (car.getId() != null) {
                carService.update(car);
            } else {
                carService.save(car);
            }
            return "redirect:/" + WelcomeController.WELCOME;
        } catch (Exception e) {
            CarRegisterController.LOG.error(e.getMessage(), e);
            if (car.getId() != null) {
                model.addAttribute("pageTitle", "Gépjármű Szerkesztés");
            } else {
                model.addAttribute("pageTitle", "Gépjármű Regisztrálása");
            }

            model.addAttribute("error", e.getMessage());
            model.addAttribute("car", car);
            model.addAttribute("carModels", carModelService.getAll());
        }
        return CarRegisterController.CARREGISTER;
    }

}
