package com.piggy.bank.web.repositories;

import com.piggy.bank.web.entities.AccessModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessModulesRepository extends JpaRepository<AccessModule, Long> {
    AccessModule findByName(String name);
}
