package com.piggy.bank.web.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for main page
 */
@Controller
@RequestMapping({"/", ""})
@Secured("ROLE_USER")
public class HomeController {
    @GetMapping
    public String index() {
        return "redirect:/budget/outcome/all";
    }
}
