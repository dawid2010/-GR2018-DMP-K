package com.piggy.bank.web.repositories.budget;

import com.piggy.bank.web.entities.budget.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
