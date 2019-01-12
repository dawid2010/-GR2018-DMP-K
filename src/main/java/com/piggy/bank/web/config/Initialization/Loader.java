package com.piggy.bank.web.config.Initialization;

import com.piggy.bank.web.entities.AccessControl;
import com.piggy.bank.web.entities.AccessModule;
import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.repositories.AccessControlRepository;
import com.piggy.bank.web.repositories.AccessModulesRepository;
import com.piggy.bank.web.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Configure application during start:
 * - add users' missing roles to DB
 */
@Component
public class Loader {

    @Autowired
    private AccessModulesRepository accessModulesRepository;
    @Autowired
    private AccessControlRepository accessControlRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(Loader.class);

    public static final Set<AccessModule> basicModules = new HashSet<AccessModule>() {
        {
            add(new AccessModule("ROLE_ADMIN"));
            add(new AccessModule("ROLE_USER"));
        }
    };

    @PostConstruct
    @Transactional
    public void initialize() {
        Set<AccessModule> dataBaseModules = new HashSet<>(accessModulesRepository.findAll());

        try {
            for (AccessModule basicModule : basicModules) {
                boolean isPresent = false;
                for (AccessModule dataBaseModule : dataBaseModules) {
                    if (basicModule.getName().equals(dataBaseModule.getName())) {
                        isPresent = true;
                        break;
                    }
                }
                if (isPresent == false) accessModulesRepository.save(basicModule);
            }

            List<AccessModule> accessModules = accessModulesRepository.findAll();
            if(personRepository.findByLogin("piggyBK") == null) {
                Person admin = personRepository.save(new Person(
                        "admin",
                        "admin",
                        "piggyBK",
                        "piggy-bank@piggybank.com",
                        passwordEncoder.encode("12345678"),
                        true));
                accessControlRepository.saveAll(Arrays.asList(
                        new AccessControl(admin, accessModules.get(0)),
                        new AccessControl(admin, accessModules.get(1))));
            }

            TimeZone.setDefault(TimeZone.getTimeZone("Poland"));
            logger.info("Spring boot application running in Europe/Warsaw timezone :" + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}