package com.piggy.bank.web.controllers;

import com.piggy.bank.web.entities.Person;
import com.piggy.bank.web.exceptions.NoPeopleFoundException;
import com.piggy.bank.web.exceptions.NoPersonFoundException;
import com.piggy.bank.web.exceptions.NoTrainingFoundException;
import com.piggy.bank.web.repositories.AccessModulesRepository;
import com.piggy.bank.web.repositories.PersonRepository;
import com.piggy.bank.web.services.PersonService;
import com.piggy.bank.web.tools.AbstractPage;
import com.piggy.bank.web.tools.URLHelper;
import com.piggy.bank.web.validators.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Controller to manage users
 */
@Controller
@RequestMapping("/user")
@Secured("ROLE_USER")
public class PersonController extends AbstractPage {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private AccessModulesRepository accessModulesRepository;
    @Autowired
    private URLHelper urlHelper;


    @GetMapping("/profile/edit")
    public String showProfileEditor(Principal principal, Model model) {
        Person person = personRepository.findByLogin(principal.getName());
        model.addAttribute("person", person);
        return "profile/edit_user_profile";
    }

    @PostMapping("/profile/edit")
    public String submitProfileEdition(@Valid Person person, BindingResult result, Model model) {
        PersonValidator.trimPersonFields(person);
        personService.updateProfile(person, result);
        if (!result.hasErrors()) {
            personRepository.save(person);
            return "redirect:/logout";
        } else
            return "profile/edit_user_profile";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}/profile/administrate/{page}")
    public String showProfileAdministrationEditor(@PathVariable(value = "page") String page,
                                                  @PathVariable("id") Person person,
                                                  Model model) {
        if (person == null) {
            throw new NoPersonFoundException();
        }
        Integer pageNumber = validateAndParsePageVariable(page);
        model.addAttribute("person", person);
        model.addAttribute("page", pageNumber);
        model.addAttribute("modules", accessModulesRepository.findAll());
        model.addAttribute("personModules", personService.getAccessModulesNamesOfPerson(person));

        return "admin/edit_user_profile";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/profile/administrate/{page}")
    public String submitProfileAdministration(@PathVariable("page") String page,
                                              @Valid Person person,
                                              BindingResult result,
                                              Model model,
                                              RedirectAttributes redirectAttributes) {
        Integer pageNumber = validateAndParsePageVariable(page);
        PersonValidator.trimPersonFields(person);
        personService.administrateUserProfile(person, result);
        if (!result.hasErrors()) {
            Person personBeforeUpdate = new Person(personRepository.findById(person.getId()).get());
            if (personBeforeUpdate.getActive() && !person.getActive()) {
                person.setToken("");
            }
            personRepository.save(person);
            redirectAttributes.addFlashAttribute("editProfileStatus", true);

            return "redirect:/user/all/" + pageNumber;
        } else {
            model.addAttribute("person", person);
            model.addAttribute("page", pageNumber);
            model.addAttribute("modules", accessModulesRepository.findAll());
            model.addAttribute("personModules", personService.getAccessModulesNamesOfPerson(person));

            return "admin/edit_user_profile";
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all/{page}")
    public String showPersonList(@PathVariable(value = "page") String page, Model model, HttpServletRequest request) {
        Integer pageNumber = validateAndParsePageVariable(page);

        Page<Person> pagedPeople = personRepository.findAll(PageRequest.of(pageNumber, RESULTS_BY_PAGE));
        if (!pagedPeople.hasContent()) {
            throw new NoPeopleFoundException();
        }
        int totalPages = pagedPeople.getTotalPages();

        List<Person> people = personService.reloadAccessModulesToShowOrdered(pagedPeople.getContent());
        model.addAttribute("baseUrlForUsers", urlHelper.removeLastItemsSplittedBySlash(request.getRequestURL().toString(), 4));
        model.addAttribute("people", people);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", pageNumber);

        return "admin/user_list";
    }
}