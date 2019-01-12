package com.piggy.bank.web.repositories;

import com.piggy.bank.web.entities.AccessControl;
import com.piggy.bank.web.entities.AccessModule;
import com.piggy.bank.web.entities.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
    List<AccessControl> findByPerson(Person person);

    List<AccessControl> findByPerson(Person person, Sort sort);
    List<AccessControl> findByAccessModule(AccessModule accessModule);
}
