package com.piggy.bank.web.validators;

import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.exceptions.NoPersonFoundException;
import com.piggy.bank.web.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

/**
 * Validator for registration process
 */
@Component
public class RegistrationValidator {

    @Autowired
    private PersonRepository personRepository;


    /**
     * Checks:
     * - email by regex
     * - if person already exists in database (by email and login)
     * - if passwords match
     *
     * @param person
     * @param errors
     */
    @Transactional
    public void validate(Object person, List<String> userData, Errors errors) {
        Person personToValidate = (Person) person;
        Person personWithSameLogin = personRepository.findByLogin(personToValidate.getLogin().toLowerCase());

        if (personWithSameLogin != null &&
                personToValidate.getLogin().toLowerCase().equals(personWithSameLogin.getLogin().toLowerCase())) {
            errors.rejectValue("login", "error.person.login.same");
        }

        if(personToValidate.getPassword().length() < 8 && personToValidate.getPassword().length() < 25){
            errors.rejectValue("password", "error.person.password.syntax");
        }

        if (!personToValidate.getPassword().equals(personToValidate.getMatchPassword())) {
            errors.rejectValue("matchPassword", "error.person.matchPassword");
        }
    }
}
