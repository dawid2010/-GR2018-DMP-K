package com.piggy.bank.web.services;

import com.piggy.bank.web.entities.AccessControl;
import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.repositories.AccessControlRepository;
import com.piggy.bank.web.repositories.PersonRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Component that check database for user's existence in security process
 */
@Service
public class PersonDetailsService implements UserDetailsService {

    private AccessControlRepository accessControlRepository;
    private PersonRepository personRepository;

    public PersonDetailsService(AccessControlRepository accessControlRepository, PersonRepository personRepository) {
        this.accessControlRepository = accessControlRepository;
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DisabledException {
        Person person = personRepository.findByLogin(login);

        if (person != null && person.getActive()) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (AccessControl aC : accessControlRepository.findByPerson(person)) {
                authorities.add(new SimpleGrantedAuthority(aC.getAccessModule().getName()));
            }

            return new User(
                    person.getLogin(),
                    person.getPassword(),
                    authorities
            );
        } else if (person != null && !person.getActive()) {
            throw new DisabledException("User account has not yet been activated.");
        } else {
            throw new UsernameNotFoundException("");
        }
    }
}
