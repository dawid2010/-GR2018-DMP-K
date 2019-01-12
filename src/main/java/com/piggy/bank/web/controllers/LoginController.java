package com.piggy.bank.web.controllers;

import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.repositories.PersonRepository;
import com.piggy.bank.web.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

/**
 * Controller for login process
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String login(Principal principal, Model model) {
        return principal != null ? "redirect:/budget/outcomes/all" : "security/login";
    }

    @GetMapping("/password/forgot")
    public String forgotPassword() {
        return "security/forgot_password";
    }

    @PostMapping("/password/forgot")
    public String forgotPassword(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString();
        Map<String, String> errors = accountService.resetPassword(email, baseUrl);
        if (errors.isEmpty()) {
            model.addAttribute("messageSuccess", "reset.password.email.sent");
            return "security/login";
        }
        model.addAttribute("errors", errors);
        return "security/forgot_password";
    }

    @GetMapping(value = "/password/reset", params = "token")
    public String resetPassword(@RequestParam("token") String token, Model model) {
        Person person = personRepository.findByToken(token);
        if (person != null) {
            model.addAttribute("person", person);
            return "security/reset_password";
        }
        model.addAttribute("messageFailure", "reset.password.failure");
        return "security/login";
    }

    @PostMapping("/password/reset")
    public String resetPassword(@Valid Person person, BindingResult bindingResult, Model model) {
        accountService.resetPersonPassword(person, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("messageSuccess", "reset.password.success");
            return "security/login";
        }
        model.addAttribute("person", person);
        return "security/reset_password";
    }
}
