package com.group2.superherosightings.controller;

import com.group2.superherosightings.dao.*;
import com.group2.superherosightings.dto.*;
import com.group2.superherosightings.service.HeroExceptions.HeroDuplicateNameException;
import com.group2.superherosightings.service.HeroServiceInterface;
import com.group2.superherosightings.service.LocationService;
import com.group2.superherosightings.service.OrganizationService;
import com.group2.superherosightings.service.SuperpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HeroController {

    //TODO : instanciate the interface or the implementation??
    @Autowired
    HeroServiceInterface heroService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    LocationService locationService;

    @Autowired
    SuperpowerService superpowerService;

    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroService.getAllHeroes();
        List<Organization> organizations = organizationService.getAllOrganization();
        List<Location> locations = locationService.getAllLocations();

        model.addAttribute("heroes", heroes);
        model.addAttribute("organizations", organizations);
        model.addAttribute("locations", locations);
        model.addAttribute("noFilterSelected", false);

        return "heroes";
    }

    @GetMapping("addHero")
    public String addHero(Model model) {
        List<Organization> organizations = organizationService.getAllOrganization();
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();

        model.addAttribute("hero", new Hero());
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "addHero";
    }

    @PostMapping("addHero")
    public String performAddHero(@Valid Hero hero, BindingResult result, HttpServletRequest request, Model model) {
        //Hero object already coming with set name and description
        //Only need to set superpower & organizations
        String superpowerID = request.getParameter("superpowerID");
        String[] organizationsIDs = request.getParameterValues("organizationID");

        //TODO : change to superpowerService when done
        hero.setSuperpower(superpowerService.getSuperpowerById(Integer.parseInt(superpowerID)));

        List<Organization> organizations = new ArrayList<>();
        if(organizationsIDs != null) {
            for(String organizationID : organizationsIDs) {
                organizations.add(organizationService.getOrganizationByID(Integer.parseInt(organizationID)));
            }
        }
        hero.setOrganizations(organizations);

        if(result.hasErrors()) {
            model.addAttribute("hero", hero);
            model.addAttribute("superpowers", superpowerService.getAllSuperpowers());
            model.addAttribute("organizations", organizationService.getAllOrganization());
            return "addHero";
        }

        heroService.newHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("heroDetail")
    public String heroDetail(Integer heroId, Model model) {

        Hero hero = heroService.getHeroByID(heroId);
        if(   hero.getSuperpower() != null){
            System.out.println(hero.getSuperpower());

        }
        List<Sighting> sightings = sightingDao.getSightingsByHero(hero);

        model.addAttribute("hero", hero);
        model.addAttribute("sightings",sightings);
        return "heroDetail";
    }

    @GetMapping("editHero")
    public String editHero(Integer heroId, Model model) {
        Hero hero = heroService.getHeroByID(heroId);
        List<Superpower> superpowers = superpowerService.getAllSuperpowers();
        List<Organization> organizations = organizationService.getAllOrganization();

        //Create a list of hero organization IDS for the HTML organization select
        List<Integer> heroOrganizationIDs = hero.getOrganizations().stream()
                .map(Organization::getOrganizationID) // Map each Organization to its ID
                .collect(Collectors.toList()); // Collect the IDs into a new list

        model.addAttribute("hero", hero);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroOrganizationsIDs", heroOrganizationIDs);

        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(@Valid Hero hero, BindingResult result,  HttpServletRequest request, Model model) throws HeroDuplicateNameException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superpowerID = request.getParameter("superpowerID");
        String[] heroOrganizations = request.getParameterValues("organizationID");

        hero.setName(name);
        hero.setDescription(description);
        hero.setSuperpower(superpowerService.getSuperpowerById(Integer.parseInt(superpowerID)));

        List<Organization> organizations = new ArrayList<>();
        if(heroOrganizations != null) {
            for(String organizationID : heroOrganizations) {
                organizations.add(organizationService.getOrganizationByID(Integer.parseInt(organizationID)));
            }
        }

        hero.setOrganizations(organizations);

        heroService.editHero(hero);


        if(result.hasErrors()) {
            model.addAttribute("hero", hero);
            model.addAttribute("superpowers", superpowerService.getAllSuperpowers());
            model.addAttribute("organizations", organizationService.getAllOrganization());
            model.addAttribute("heroOrganizationsIDs", hero.getOrganizations().stream()
                    .map(Organization::getOrganizationID) // Map each Organization to its ID
                    .collect(Collectors.toList())); // Collect the IDs into a new list);

            return "editHero";
        }

        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(Integer heroId) {
        heroService.deleteHeroById(heroId);
        return "redirect:/heroes";
    }

    @GetMapping("filterHeroes")
    public String displayFilteredHeroes(Integer[] locationID, Integer[] organizationID, Model model) {
        // Using a set to not have duplicate values
        Set<Hero> filteredHeroes = new HashSet<>();
        List<Organization> organizations = organizationService.getAllOrganization();
        List<Location> locations = locationService.getAllLocations();

        model.addAttribute("organizations", organizations);
        model.addAttribute("locations", locations);

        // If form inputs are both null
        // Return heroes page with noFilterSelected true to show error message
        if (locationID == null && organizationID == null) {
            model.addAttribute("heroes", filteredHeroes); // Show an empty list of heroes
            model.addAttribute("noFilterSelected", true);
            return "heroes";
        } else {

            List<Organization> reqOrganizations = new ArrayList<>();
            List<Location> reqLocations = new ArrayList<>();

            List<Integer> reqLocationsIDs = new ArrayList<>();
            List<Integer> reqOrganizationsIDs = new ArrayList<>();

            // Check if both organizationID and locationID are not null
            if (organizationID != null && locationID != null) {

                // Add selected organizations and locations to the respective lists
                for (Integer orgID : organizationID) {
                    reqOrganizations.add(organizationService.getOrganizationByID(orgID));
                }

                for (Integer locID : locationID) {
                    reqLocations.add(locationService.getLocationByID(locID));
                }

                // Reassign a list of hero organization IDs for the HTML organization select
                reqOrganizationsIDs = reqOrganizations.stream()
                        .map(Organization::getOrganizationID) // Map each Organization to its ID
                        .collect(Collectors.toList()); // Collect the IDs into a new list

                // Reassign a list of hero locations IDs for the HTML location select
                reqLocationsIDs = reqLocations.stream()
                        .map(Location::getLocationID) // Map each Location to its ID
                        .collect(Collectors.toList()); // Collect the IDs into a new list

                List<Hero> heroesByOrganizations = new ArrayList<>();
                List<Hero> heroesByLocations = new ArrayList<>();

                // Get heroes that match the selected organizations
                for (Organization reqOrganization : reqOrganizations) {
                    heroesByOrganizations.addAll(heroService.getHerosByOrganization(reqOrganization));
                }

                // Get heroes that match the selected locations
                for (Location reqLocation : reqLocations) {
                    heroesByLocations.addAll(heroService.getHerosByLocation(reqLocation));
                }

                // Find heroes that match both the selected organizations and locations
                for (Hero h : heroesByLocations) {
                    if (heroesByOrganizations.contains(h)) {
                        filteredHeroes.add(h);
                    }
                }

                for (Hero h : heroesByOrganizations) {
                    if (heroesByLocations.contains(h)) {
                        filteredHeroes.add(h);
                    }
                }

                model.addAttribute("reqOrganizationsIDs", reqOrganizationsIDs);
                model.addAttribute("reqLocationsIDs", reqLocationsIDs);
            } else if (organizationID != null) {
                // Only organizationID is selected
                // Add heroes based on selected organizations

                for (Integer orgID : organizationID) {
                    reqOrganizations.add(organizationService.getOrganizationByID(orgID));
                }

                for (Organization reqOrganization : reqOrganizations) {
                    filteredHeroes.addAll(heroService.getHerosByOrganization(reqOrganization));
                }

                // Reassign a list of hero organization IDs for the HTML organization select
                reqOrganizationsIDs = reqOrganizations.stream()
                        .map(Organization::getOrganizationID) // Map each Organization to its ID
                        .collect(Collectors.toList()); // Collect the IDs into a new list

                model.addAttribute("reqOrganizationsIDs", reqOrganizationsIDs);

                // Add an empty list of locations
                model.addAttribute("reqLocationsIDs", reqLocationsIDs);
            } else if (locationID != null) {
                // Only locationID is selected
                // Add heroes based on selected locations

                // Add an empty list of organizations
                model.addAttribute("reqOrganizationsIDs", reqOrganizationsIDs);

                for (Integer locID : locationID) {
                    reqLocations.add(locationService.getLocationByID(locID));
                }

                for (Location reqLocation : reqLocations) {
                    filteredHeroes.addAll(heroService.getHerosByLocation(reqLocation));
                }

                // Reassign a list of hero locations IDs for the HTML location select
                reqLocationsIDs = reqLocations.stream()
                        .map(Location::getLocationID) // Map each Location to its ID
                        .collect(Collectors.toList()); // Collect the IDs into a new list

                model.addAttribute("reqLocationsIDs", reqLocationsIDs);
            }

            model.addAttribute("noFilterSelected", false);
            model.addAttribute("heroes", filteredHeroes);
            return "filterHeroes";
        }
    }
}
