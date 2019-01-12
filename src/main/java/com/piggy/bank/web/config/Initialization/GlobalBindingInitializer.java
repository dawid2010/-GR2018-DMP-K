package com.piggy.bank.web.config.Initialization;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Date;

/**
 * Help for date problems
 */
@ControllerAdvice
public class GlobalBindingInitializer {
    @InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
