package com.piggy.bank.web.controllers;

import com.piggy.bank.web.entities.budget.Outcome;
import com.piggy.bank.web.entities.budget.Income;
import com.piggy.bank.web.exceptions.AlreadyOpenedTrainingException;
import com.piggy.bank.web.repositories.budget.IncomeRepository;
import com.piggy.bank.web.repositories.budget.OutcomeRepository;
import com.piggy.bank.web.services.PersonService;
import com.piggy.bank.web.services.BudgetService;
import com.piggy.bank.web.tools.AbstractPage;
import com.piggy.bank.web.tools.URLHelper;
import com.piggy.bank.web.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

/**
 * Controller of budget operations
 */
@Controller
@RequestMapping("/budget")
@Secured("ROLE_USER")
public class BudgetController extends AbstractPage {

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private PersonService personService;
    @Autowired
    private OutcomeRepository outcomeRepository;
    @Autowired
    private URLHelper urlHelper;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccessControlRepository accessControlRepository;
    @Autowired
    private AccessModulesRepository accessModulesRepository;
    @Autowired
    private IncomeRepository incomeRepository;

    private static final Logger logger = LoggerFactory.getLogger(BudgetController.class);


    @Secured("ROLE_ADMIN")
    @GetMapping("/outcome/all")
    public String showAllOutcomes(Model model) {
        Outcome outcome = new Outcome();
        model.addAttribute("outcome", outcome);
        model.addAttribute("outcomes",
                budgetService.getAllOutcomes());
        return "admin/outcomes";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/income/all")
    public String showAllIncomes(Model model) {
        Income income = new Income();
        model.addAttribute("income", income);
        model.addAttribute("incomes",
                budgetService.getAllIncomes());
        return "admin/incomes";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/outcome/add")
    public String addOutcome(@Valid Outcome outcome,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("outcome", outcome);
            model.addAttribute("outcomes",
                    budgetService.getAllOutcomes());
            model.addAttribute("formError", true);
            return "admin/outcomes";
        } else {
            outcome.setName(outcome.getName().toLowerCase());
            outcomeRepository.save(outcome);
//            ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
//            RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//            HttpEntity<Outcome> request = new HttpEntity<>(outcome);
//            restTemplate.postForObject("", request, Outcome.class);
            return "redirect:/budget/outcome/all";
        }
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/income/add")
    public String addIncome(@Valid Income income,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("income", income);
            model.addAttribute("incomes",
                    budgetService.getAllIncomes());
            model.addAttribute("formError", true);
            return "admin/incomes";
        } else {
            income.setName(income.getName().toLowerCase());
            incomeRepository.save(income);
//
//            HttpEntity<Income> request = new HttpEntity<>(income);
//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.postForObject("http://localhost:8080/income/add", request, Income.class);

            return "redirect:/budget/income/all";
        }
    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("/template/{id}/delete")
//    public String deleteTrainingTemplate(@PathVariable("id") Outcome outcome, Model model) {
//            outcomeRepository.delete(outcome);
//
//        return "redirect:/budget/template/all";
//    }
}