package com.group2.superherosightings.controller;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Sighting;
import com.group2.superherosightings.service.HeroServiceInterface;
import com.group2.superherosightings.service.LocationService;
import com.group2.superherosightings.service.EntityNotFoundException;
import com.group2.superherosightings.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SightingController {

    private final SightingService sightingService;
    private final HeroServiceInterface heroService;
    private final LocationService locationService;

    @Autowired
    public SightingController(SightingService sightingService,
                              HeroServiceInterface heroService,
                              LocationService locationService) {
        this.sightingService = sightingService;
        this.heroService = heroService;
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String displayLatestSightings(Model model) {
        List<Sighting> sightings = sightingService.getAllSightings();
        model.addAttribute("sightings", sightings);
        return "redirect:/index.html";
    }

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingService.getAllSightings();
        List<Hero> heroes = heroService.getAllHeroes();
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("sighting",new Sighting());
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(@Valid Sighting sighting, BindingResult result, HttpServletRequest request, Model model) throws Exception {
        String[] heroIDs  = request.getParameterValues("heroID");
        String locationID= request.getParameter("locationID");
        String sightingDate=request.getParameter("sightingDate");
        sighting.setLocation(locationService.getLocationByID(Integer.parseInt(locationID)));
        sighting.setSightingDate(LocalDateTime.parse(sightingDate));
        List<Hero> heroSighted= new ArrayList<>();
        if(heroIDs !=null)  {
            for(String heroID:heroIDs ) {
                heroSighted.add(heroService.getHeroByID(Integer.parseInt(heroID)));
            }
        }

        sighting.setHeroes(heroSighted);
//        LocalDateTime fakeDate = LocalDateTime.now().withNano(0);
//        sighting.setSightingDate(fakeDate);
//        if (result.hasErrors()) {
//            List<Hero> heroes = heroService.getAllHeroes();
//            List<Location> locations = locationService.getAllLocations();
//            model.addAttribute("heroes", heroes);
//            model.addAttribute("locations", locations);
//            model.addAttribute("sighting",new Sighting());
//            return "addSighting";
//        }
        sightingService.addSighting(sighting);
        return "redirect:/sightings";
    }

    @PostMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("sightingID"));
        sightingService.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer sightingID, Model model) throws EntityNotFoundException {
        Sighting sighting = sightingService.getSightingById(sightingID);
        List<Hero> heroes = heroService.getAllHeroes();
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result, HttpServletRequest request, Model model) throws Exception {
        if (result.hasErrors()) {
            List<Hero> heroes = heroService.getAllHeroes();
            List<Location> locations = locationService.getAllLocations();
            model.addAttribute("heroes", heroes);
            model.addAttribute("locations", locations);
            return "editSighting";
        }
        sightingService.updateSighting(sighting);
        return "redirect:/sightings";
    }
}
