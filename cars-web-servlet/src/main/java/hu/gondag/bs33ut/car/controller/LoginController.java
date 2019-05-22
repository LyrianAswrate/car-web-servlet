package hu.gondag.bs33ut.car.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hu.gondag.bs33ut.car.dto.LoginModel;
import hu.gondag.bs33ut.car.security.ApplicationSecurityService;
import hu.gondag.bs33ut.car.security.ApplicationUserDetails;

@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    public static final String LOGIN = "login";

    @Autowired
    private ApplicationSecurityService securityService;

    @GetMapping(path = { "/", "/login" })
    public String login(Map<String, Object> model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof ApplicationUserDetails) {
            return "redirect:/" + WelcomeController.WELCOME;
        }
        return LoginController.LOGIN;
    }

    @PostMapping(path = { "/", "/login" })
    public String login(Model model, @ModelAttribute LoginModel login) {
        try {
            securityService.doLogin(login.getUsername(), login.getPassword());
            return "redirect:/" + WelcomeController.WELCOME;
        } catch (Exception e) {
            LoginController.LOG.error(e.getMessage(), e);
            model.addAttribute("loginError", "true");
        }
        return LoginController.LOGIN;
    }

}
