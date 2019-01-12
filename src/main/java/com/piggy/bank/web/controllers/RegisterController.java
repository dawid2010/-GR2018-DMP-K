package com.piggy.bank.web.controllers;

import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.services.AccountService;
import com.piggy.bank.web.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * Controller for registration process
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String register(Principal principal, Model model) {
        if (principal != null) {
            return "redirect:/";
        }
        model.addAttribute("person", new Person());
        return "security/register";
    }

    @PostMapping
    public String register(Model model,
                           @Valid Person person,
                           BindingResult bindingResult,
                           HttpServletRequest request) {
        PersonValidator.trimPersonFields(person);
        String baseUrl = request.getRequestURL().toString();
        accountService.createAccount(person, bindingResult, baseUrl);
        if (bindingResult.hasErrors()) {
            return "security/register";
        }
        model.addAttribute("messageSuccess", "email.register.resend.success");
        return "security/login";
    }

    @GetMapping(value = "/confirm", params = "token")
    public String confirmRegistrationProcess(@RequestParam("token") String token, Model model) {
        if (accountService.confirmRegistrationProcess(token)) {
            model.addAttribute("messageSuccess", "register.confirmation.result.description.success");
        } else {
            model.addAttribute("messageFailure", "register.confirmation.result.description.failure");
        }
        return "security/login";
    }

    @GetMapping("/confirm/resend")
    public String resendConfirmationEmail() {
        return "security/resend_confirmation_email";
    }

    @PostMapping("/confirm/resend")
    public String resendConfirmationEmail(@RequestParam("email") String email,
                                          Model model,
                                          HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString();
        Map<String, String> errors = accountService.resendConfirmationEmail(email, baseUrl);
        if (errors.isEmpty()) {
            model.addAttribute("messageSuccess", "email.register.resend.success");
            return "security/login";
        }
        model.addAttribute("errors", errors);
        return "security/resend_confirmation_email";
    }
}
