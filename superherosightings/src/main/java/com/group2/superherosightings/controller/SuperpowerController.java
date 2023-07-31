package com.group2.superherosightings.controller;

import com.group2.superherosightings.dao.HeroDao;
import com.group2.superherosightings.dao.SuperpowerDao;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.dto.Superpower;
import com.group2.superherosightings.service.SuperpowerDataValidationException;
import com.group2.superherosightings.service.SuperpowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SuperpowerController {

    @Autowired
    SuperpowerServiceImpl superpowerServiceImpl;

    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();


    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {

        List<Superpower> superpowers = superpowerServiceImpl.getAllSuperpowers();
        model.addAttribute("superpowers",superpowers);

        return "superpowers";
    }


    @PostMapping("addSuperpower")
    public String performAddSuperpower(HttpServletRequest request) throws SuperpowerDataValidationException {
        String name = request.getParameter("name");

        Superpower superpower = new Superpower();
        superpower.setName(name);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if(violations.isEmpty()) {
            superpowerServiceImpl.addSuperpower(superpower);
        }

        return "redirect:/superpowers";
    }

    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(HttpServletRequest request) {
        int superpowerId = Integer.parseInt(request.getParameter("id"));
        superpowerServiceImpl.deleteSuperpowerById(superpowerId);

        return "redirect:/superpowers";
    }

    @GetMapping("editSuperpower")
    public String editSuperpower(HttpServletRequest request, Model model) {
        int superpowerId = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerServiceImpl.getSuperpowerById(superpowerId);

        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }


    @PostMapping("editSuperpower")
    public String performEditSuperpower(HttpServletRequest request) {
        int superpowerId = Integer.parseInt(request.getParameter("id"));
        Superpower superpower = superpowerServiceImpl.getSuperpowerById(superpowerId);

        superpower.setName(request.getParameter("name"));


        superpowerServiceImpl.updateSuperpower(superpower);

        return "redirect:/superpowers";
    }
}
