package com.piggy.bank.web.services;

import com.piggy.bank.web.entities.budget.Outcome;
import com.piggy.bank.web.entities.budget.Income;
import com.piggy.bank.web.repositories.budget.IncomeRepository;
import com.piggy.bank.web.repositories.budget.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service to handle operations of any given outcome or income
 */
@Service
public class BudgetService {

    private OutcomeRepository outcomeRepository;

    private IncomeRepository incomeRepository;

    @Autowired
    public BudgetService(OutcomeRepository outcomeRepository,
                         IncomeRepository incomeRepository) {
        this.outcomeRepository = outcomeRepository;
        this.incomeRepository = incomeRepository;
    }
    @Transactional
    public List<Outcome> getAllOutcomes() {
        return outcomeRepository.findAll();
    }

    @Transactional
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }
}