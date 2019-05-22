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

import hu.gondag.bs33ut.car.dto.CarModelModel;
import hu.gondag.bs33ut.car.security.service.CarModelService;

@Controller
public class CarModelRegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(CarRegisterController.class);

    public static final String CARMODELREGISTER = "carmodelregister";

    @Autowired
    private CarModelService carModelService;

    @GetMapping(path = { "/carmodelregister", })
    public String register(Model model) {
        model.addAttribute("pageTitle", "Gépjármű Model Regisztrálása");
        return CarModelRegisterController.CARMODELREGISTER;
    }

    @PostMapping(path = { "/carModelRegisterEdit" })
    public String edit(Model model, @RequestParam Long carModelId) {
        model.addAttribute("pageTitle", "Gépjármű Model Szerkesztés");
        model.addAttribute("carModel", carModelService.getById(carModelId));
        return CarModelRegisterController.CARMODELREGISTER;
    }

    @PostMapping(path = { "/carmodelregister" })
    public String save(Model model, @ModelAttribute CarModelModel carModel) {
        try {
            if (carModel.getId() != null) {
                carModelService.update(carModel);
            } else {
                carModelService.save(carModel);
            }
            return "redirect:/" + WelcomeController.WELCOME;
        } catch (Exception e) {
            CarModelRegisterController.LOG.error(e.getMessage(), e);
            if (carModel.getId() != null) {
                model.addAttribute("pageTitle", "Gépjármű Model Szerkesztés");
            } else {
                model.addAttribute("pageTitle", "Gépjármű Model Regisztrálása");
            }

            model.addAttribute("error", e.getMessage());
            model.addAttribute("carModel", carModel);
        }
        return CarModelRegisterController.CARMODELREGISTER;
    }

}
