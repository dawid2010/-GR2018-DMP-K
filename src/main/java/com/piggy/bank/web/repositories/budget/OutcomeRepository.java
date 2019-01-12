package com.piggy.bank.web.repositories.budget;

import com.piggy.bank.web.entities.budget.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
    Outcome findByName(String name);
}
