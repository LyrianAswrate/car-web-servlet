package hu.gondag.bs33ut.car.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hu.gondag.bs33ut.car.dto.RegistrationModel;
import hu.gondag.bs33ut.car.exceptions.RegistrationPasswordValidationException;
import hu.gondag.bs33ut.car.security.service.UserService;

@Controller
public class RegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    public static final String REGISTRATION = "registration";

    @Autowired
    private UserService userService;

    @GetMapping(path = "/registration")
    public String registration(Map<String, Object> model) {
        return RegistrationController.REGISTRATION;
    }

    @PostMapping(path = "/registration")
    public String registration(Model model, @ModelAttribute RegistrationModel registration) {
        try {
            if ((registration.getPassword() != null) && (registration.getRePassword() != null)
                    && registration.getPassword().equals(registration.getRePassword())) {
                userService.createUser(registration.getUsername(), registration.getFullname(), registration.getPassword());
                return LoginController.LOGIN;
            }
            throw new RegistrationPasswordValidationException("A kétt jelszó nem egyezik meg!");
        } catch (RegistrationPasswordValidationException e) {
            RegistrationController.LOG.error(e.getMessage(), e);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registration", registration);
        } catch (Exception e) {
            RegistrationController.LOG.error(e.getMessage(), e);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registration", registration);
        }
        return RegistrationController.REGISTRATION;
    }

}
