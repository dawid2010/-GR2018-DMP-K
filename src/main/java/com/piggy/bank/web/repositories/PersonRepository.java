package com.piggy.bank.web.repositories;

import com.piggy.bank.web.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);

    Person findByEmail(String email);

    Person findByToken(String token);

    Set<Person> findAllByIdIn(List<Long> ids);
}
