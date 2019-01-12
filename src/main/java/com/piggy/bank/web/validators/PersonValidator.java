package com.piggy.bank.web.validators;

import com.piggy.bank.web.entities.AccessModule;
import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.repositories.AccessModulesRepository;
import com.piggy.bank.web.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.List;

/**
 * Validator for profile edition process
 */
@Component
public class PersonValidator {

    private final String EMAIL_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*" +
            "@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccessModulesRepository accessModulesRepository;

    /**
     * Checks:
     * - if passwords match
     *
     * @param errors
     */
    public void validatePasswordForUpdateProfile(Person personToValidate, Errors errors) {

        if (personToValidate.getPassword().length() < 8 || personToValidate.getPassword().length() > 25) {
            errors.rejectValue("password", "error.person.password.syntax");
        }

        if (!personToValidate.getPassword().equals(personToValidate.getMatchPassword())) {
            errors.rejectValue("matchPassword", "error.person.matchPassword");
        }
    }

    /**
     * Checks:
     * - if passwords match
     *
     * @param errors
     */
    public void validatePassword(Person personToValidate, Errors errors) {

        if (personToValidate.isCheckBoxChangePassword()) {
            if (personToValidate.getPassword().length() < 8 || personToValidate.getPassword().length() > 25) {
                errors.rejectValue("password", "error.person.password.syntax");
            }

            if (!personToValidate.getPassword().equals(personToValidate.getMatchPassword())) {
                errors.rejectValue("matchPassword", "error.person.matchPassword");
            }
        }
    }

    public void validatePasswordAndAccessModules(Person person, Errors errors) {
        List<AccessModule> accessModules = accessModulesRepository.findAllById(person.getSelectedModules());
        boolean found = false;

        for (AccessModule ac : accessModules) {
            if (ac.getName().equals("ROLE_USER")) {
                found = true;
                break;
            }
        }

        if (!found) {
            errors.rejectValue("selectedModules", "error.person.accessModules.empty");
        }
        validatePassword(person, errors);
    }


    /**
     * Trim all person fields
     * @param person
     */
    public static void trimPersonFields(Person person) {
        if(person.getEmail() != null)
            person.setEmail(person.getEmail().trim());
        if(person.getFirstName() != null)
            person.setFirstName((person.getFirstName().trim()));
        if(person.getLastName() !=null)
            person.setLastName(person.getLastName().trim());
        if(person.getPassword() != null)
            person.setPassword(person.getPassword().trim());
        if(person.getMatchPassword() != null)
            person.setMatchPassword(person.getMatchPassword().trim());
    }
}