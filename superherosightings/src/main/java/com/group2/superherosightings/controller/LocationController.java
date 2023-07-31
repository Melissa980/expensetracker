package com.group2.superherosightings.controller;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.service.LocationDataValidationException;
import com.group2.superherosightings.service.LocationService;
import com.group2.superherosightings.service.OrganizationDataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
//@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    @GetMapping("locations")
    public String getAllLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("location", new Location());
        model.addAttribute("errors", violations);
        return "locations";
    }

    @GetMapping("locationDetails")
    public String locationDetails(Integer id, Model model) {
        Location location = locationService.getLocationByID(id);
        model.addAttribute("location", location);
        return "locationDetails";
    }


//    @GetMapping("addLocation")
//    public String addLocation(Model model) {
//
//        Location location = new Location();
//
//        model.addAttribute("location", location);
//        return "addLocation";
//    }

    @PostMapping("locations")
    public String performAddLocation(@Valid Location location, BindingResult result, HttpServletRequest request, Model model) throws LocationDataValidationException {
        if(result.hasErrors()) {
            model.addAttribute("location", location);
            model.addAttribute("locations", locationService.getAllLocations());
            return "locations";
        }

        locationService.addLocation(location);

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteOrganization(Integer id) {
        locationService.deleteLocationByID(id);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(Integer id, Model model) {
        Location location = locationService.getLocationByID(id);
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("location", location);
        return "editLocation";
    }


    @PostMapping("editLocation")
    public String performEditLocation(@Valid Location location, BindingResult result, HttpServletRequest request, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("location", location);
            return "editLocation";
        }
        locationService.updateLocation(location.getLocationID(), location);
        return "redirect:/locations";
    }
}
