package com.piggy.bank.web.services;

import com.piggy.bank.web.entities.AccessControl;
import com.piggy.bank.web.entities.AccessModule;
import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.repositories.AccessModulesRepository;
import com.piggy.bank.web.repositories.PersonRepository;
import com.piggy.bank.web.tools.TokenGenerator;
import com.piggy.bank.web.tools.URLHelper;
import com.piggy.bank.web.validators.PersonValidator;
import com.piggy.bank.web.validators.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.*;

/**
 * Service to handle all account's operations
 */
@Service
public class AccountService {

    private PersonRepository personRepository;
    private AccessModulesRepository accessModulesRepository;
    private PasswordEncoder passwordEncoder;
    private RegistrationValidator registrationValidator;
    private TokenGenerator tokenGenerator;
    private PersonValidator personValidator;
    private URLHelper URLHelper;

    @Autowired
    public AccountService(PersonRepository personRepository,
                          AccessModulesRepository accessModulesRepository,
                          PasswordEncoder passwordEncoder,
                          RegistrationValidator registrationValidator,
                          TokenGenerator tokenGenerator,
                          PersonValidator personValidator,
                          URLHelper URLHelper) {
        this.personRepository = personRepository;
        this.accessModulesRepository = accessModulesRepository;
        this.passwordEncoder = passwordEncoder;
        this.registrationValidator = registrationValidator;
        this.tokenGenerator = tokenGenerator;
        this.personValidator = personValidator;
        this.URLHelper = URLHelper;
    }


    /**
     * Validate person, encode his password, add to USER_ROLE and add him to DB,
     * give him role of user and set him confirmation token (sends mail also)
     *
     * @param person
     * @param bindingResult
     * @param baseUrl - context url needed to send proper links in email
     */
    @Transactional
    public void createAccount(Person person,
                              BindingResult bindingResult,
                              String baseUrl) {
        List<String> userData = new ArrayList<>();
        registrationValidator.validate(person, userData, bindingResult);

        if (!bindingResult.hasErrors()) {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            person.setActive(true);
            Set<AccessControl> accessControls = new HashSet<>();
            AccessModule accessModule = accessModulesRepository.findByName("ROLE_ADMIN");
            accessControls.add(new AccessControl(person, accessModule));
            person.setAccessControls(accessControls);
            String token = tokenGenerator.generateToken();
            person.setToken(token);
            personRepository.save(person);
        }
    }

    /**
     * Find user by given token and if exists, check if is not active
     * and set its token to empty string and activate him
     *
     * @param token
     * @return true/false - depends on result of process
     */
    @Transactional
    public boolean confirmRegistrationProcess(String token) {
        Person person = personRepository.findByToken(token);
        if (person != null && !person.getActive()) {
            person.setToken("");
            person.setActive(true);
            personRepository.save(person);
            return true;
        }
        return false;
    }

    /**
     * Find person by email, check if exists if is already active and if token is empty,
     * if not - resend confirmation email
     * @param email
     * @param baseUrl - context url needed to send proper links in email
     * @return map of errors - if is empty, mail was sent
     */
    @Transactional
    public Map<String, String> resendConfirmationEmail(String email, String baseUrl) {
        Map<String, String> errors = new HashMap<>();
        Person person = personRepository.findByEmail(email.toLowerCase());
        if (person == null) {
            errors.put("email", "email.notFound");
        } else if (person.getActive()) {
            errors.put("active", "register.confirmation.resend.user.alreadyActive");
        } else if (person.getToken().isEmpty()) {
            errors.put("token", "register.confirmation.resend.token.empty");
        } else {
            String adjustedURL = URLHelper.removeLastItemsSplittedBySlash(baseUrl, 2);
        }
        return errors;
    }

    /**
     * Find person by given email, check if is active and if he pass validation,
     * Set him new token, save him to DB and send user mail with token
     * @param email
     * @param baseUrl - context url needed to send proper links in email
     * @return map of errors - if is empty, process was successful
     */
    @Transactional
    public Map<String, String> resetPassword(String email, String baseUrl) {
        Map<String, String> errors = new HashMap<>();
        Person person = personRepository.findByEmail(email.toLowerCase());
        if (person == null) {
            errors.put("error.password.reset.user.notFound", "email.notFound");
        } else if (!person.getActive()) {
            errors.put("error.password.reset.user.nonActive", "reset.password.user.nonActive");
        } else {
            person.setToken(tokenGenerator.generateToken());
            person = personRepository.save(person);
            String contextAdjustedURL = URLHelper.removeLastItemsSplittedBySlash(baseUrl, 1);
        }
        return errors;
    }

    /**
     * Validate given password and if it pass set new password, set token to empty string and save user to DB
     *
     * @param person
     * @param bindingResult
     */
    @Transactional
    public void resetPersonPassword(Person person, BindingResult bindingResult) {
        personValidator.validatePassword(person, bindingResult);
        if (!bindingResult.hasErrors()) {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            person.setToken("");
            personRepository.save(person);
        }
    }
}
