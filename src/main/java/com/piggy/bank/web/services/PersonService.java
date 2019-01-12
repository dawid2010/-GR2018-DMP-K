package com.piggy.bank.web.services;

import com.piggy.bank.web.entities.*;
import com.piggy.bank.web.repositories.budget.OutcomeRepository;
import com.piggy.bank.web.tools.AbstractPage;
import com.piggy.bank.web.tools.JSONHelper;
import com.piggy.bank.web.validators.PersonValidator;
import com.piggy.bank.web.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service to handle operations about Person (user/coach/admin)
 */
@Service
public class PersonService extends AbstractPage {
    private PersonRepository personRepository;
    private AccessModulesRepository accessModulesRepository;
    private AccessControlRepository accessControlRepository;
    private PersonValidator personValidator;
    private PasswordEncoder passwordEncoder;
    private OutcomeRepository outcomeRepository;
    private JSONHelper jsonHelper;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         AccessModulesRepository accessModulesRepository,
                         AccessControlRepository accessControlRepository,
                         PersonValidator personValidator,
                         PasswordEncoder passwordEncoder,
                         OutcomeRepository outcomeRepository,
                         JSONHelper jsonHelper) {
        this.personRepository = personRepository;
        this.accessModulesRepository = accessModulesRepository;
        this.accessControlRepository = accessControlRepository;
        this.personValidator = personValidator;
        this.passwordEncoder = passwordEncoder;
        this.outcomeRepository = outcomeRepository;
        this.jsonHelper = jsonHelper;
    }

    /**
     * @param accessModuleName
     * @return list of people of given access module
     */
    public List<Person> getPeopleByAccessModule(String accessModuleName) {
        List<Person> people = new ArrayList<>();
        List<AccessControl> accessControlList =
                accessControlRepository.findByAccessModule(accessModulesRepository.findByName(accessModuleName));
        for (AccessControl aC : accessControlList) {
            people.add(personRepository.findById(aC.getPerson().getId()).get());
        }
        return people;
    }
    /**
     * Update profile process made by admin
     *
     * @param person
     * @param result
     */
    public void administrateUserProfile(Person person, BindingResult result) {


        personValidator.validatePasswordAndAccessModules(person, result);
        if (!person.isCheckBoxChangePassword()){
            person.setPassword(personRepository.findById(person.getId()).get().getPassword());
        } else {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
        }
        if (!result.hasErrors()) {
            accessControlRepository.deleteAll(accessControlRepository.findByPerson(person));

            if (person.getSelectedModules() != null) {
                List<AccessModule> accessModules = accessModulesRepository.findAllById(person.getSelectedModules());
                for (AccessModule accessModule : accessModules) {
                    accessControlRepository.save(new AccessControl(person, accessModule));
                }
            }
        }
    }

    /**
     * Update profile process made by user himself
     * @param person
     * @param result
     */
    public void updateProfile(Person person, BindingResult result){
        personValidator.validatePasswordForUpdateProfile(person, result);

        if (!result.hasErrors())
            person.setPassword(passwordEncoder.encode(person.getPassword()));
    }

    /**
     * @param person
     * @return access modules of given person
     */
    public List<String> getAccessModulesNamesOfPerson(Person person){
        List<String> accessModulesNames = new ArrayList<>();
        for(AccessControl aC : accessControlRepository.findByPerson(person)){
            accessModulesNames.add(accessModulesRepository.findById(aC.getAccessModule().getId()).get().getName());
        }
        return accessModulesNames;
    }


    public List<String> parseUsersToJsons(List<Person> people) {
        List<String> jsonsAsStrings = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        people.forEach(p -> {
            JSONHelper.JSON json = jsonHelper.createJson();
            List<String> accessModules = new ArrayList<>();
            p.getAccessControls().forEach(aC -> accessModules.add(aC.getAccessModule().getName()));
            json.addField("id", p.getId().toString())
                    .addField("login", p.getLogin())
                    .addField("firstName", p.getFirstName())
                    .addField("lastName", p.getLastName())
                    .addField("email", p.getEmail())
                    .addField("accountStatus", p.getActive().toString())
                    .addField("registrationDate", dateFormat.format(new Date(p.getAccountCreationDate().getTime())))
                    .addTable("accessModules", accessModules);

            jsonsAsStrings.add(json.toString());
        });

        return jsonsAsStrings;
    }

    public List<Person> reloadAccessModulesToShowOrdered(List<Person> people) {
        for (Person person : people) {
            person.setAccessControls(new LinkedHashSet<>(accessControlRepository.findByPerson(person, new Sort(Sort.Direction.DESC, "accessModule_name"))));
        }
        return people;
    }
}