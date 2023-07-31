package com.group2.superherosightings.controller;

import com.group2.superherosightings.dao.*;
import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.service.OrganizationDataValidationException;
import com.group2.superherosightings.service.OrganizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrganizationController {

  @Autowired
  HeroDao heroDao;

  @Autowired
  OrganizationServiceImpl organizationService;

  Set<ConstraintViolation<Organization>> violations = new HashSet<>();

  @GetMapping("organizations")
  public String displayAllOrganizations(Model model) {
    List<Organization> organizations = organizationService.getAllOrganization();
    List<Hero> heroes = heroDao.getAllHeroes();
    model.addAttribute("organizations", organizations);
    model.addAttribute("heroes", heroes);
    model.addAttribute("errors", violations);
    return "organizations";
  }

  @GetMapping("organizationDetails")
  public String organizationDetails(Integer id, Model model) {
    Organization organization = organizationService.getOrganizationByID(id);
    model.addAttribute("organization", organization);
    return "organizationDetails";
  }

  @PostMapping("addOrganization")
  public String addOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) throws OrganizationDataValidationException {
    String[] heroIds = request.getParameterValues("heroId");

    List<Hero> heroes = new ArrayList<>();
      for (String heroId : heroIds) {
        heroes.add(heroDao.getHeroById(Integer.parseInt(heroId)));
      }

    organization.setHeroes(heroes);

    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    violations = validate.validate(organization);

    if(violations.isEmpty()) {
      organizationService.addOrganization(organization);
    }

    return "redirect:/organizations";
  }

  @GetMapping("deleteOrganization")
  public String deleteOrganization(Integer id) {
    organizationService.deleteOrganizationByID(id);

    return "redirect:/organizations";
  }

  @GetMapping("editOrganization")
  public String editOrganization(Integer id, Model model) {
    Organization organization = organizationService.getOrganizationByID(id);
    List<Hero> heroes = heroDao.getAllHeroes();
    for (Hero hero: heroes) {
      hero.setOrganizations(null);

    }
    model.addAttribute("organization", organization);
    model.addAttribute("heroes", heroes);
    return "editOrganization";
  }


  @PostMapping("editOrganization")
  public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
    String[] heroIds = request.getParameterValues("heroId");

    List<Hero> heroes = new ArrayList<>();
    if (heroIds != null) {
      for(String heroId : heroIds) {
        heroes.add(heroDao.getHeroById(Integer.parseInt(heroId)));
      }
    }


    organization.setHeroes(heroes);

    if(result.hasErrors()) {
      model.addAttribute("organization", organization);
      model.addAttribute("heroes", heroes);
      return "editOrganization";
    }
    organizationService.updateOrganization(organization);
    return "redirect:/organizations";
  }

}
