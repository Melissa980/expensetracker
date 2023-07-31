package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SightingDaoDBTest {

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    LocalDateTime date = LocalDateTime.now().withNano(0);

    @BeforeEach
    public void setUp() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingID());
        }

        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getHeroId());
        }
    }

    @Test
    @DisplayName("Add and Get Sighting By ID")
    public void testAddAndGetSightingByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower = superpowerDao.addSuperpower(superpower);

        Location location = new Location();
        location.setName("Location Name Test");
        location.setDescription("Location Description Test");
        location.setAddress("Location Address test");
        location.setLatitude("1.1203");
        location.setLongitude("2.345212");
        location = locationDao.addLocation(location);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(date.withNano(0));
        sighting.setHeroes(heroes);
        sighting = sightingDao.addSighting(sighting);

        Sighting sightingFromDao = sightingDao.getSightingById(sighting.getSightingID());

        assertEquals(sighting, sightingFromDao);
    }

    @Test
    @DisplayName("Get All Sightings")
    public void testGetAllSightings() {
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower = superpowerDao.addSuperpower(superpower);

        Location location = new Location();
        location.setName("Location Name Test");
        location.setDescription("Location Description Test");
        location.setAddress("Location Address test");
        location.setLatitude("1.1203");
        location.setLongitude("2.345212");
        location = locationDao.addLocation(location);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Sighting sighting1 = new Sighting();
        sighting1.setLocation(location);
        sighting1.setSightingDate(date);
        sighting1.setHeroes(heroes);
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setLocation(location);
        sighting2.setSightingDate(date);
        sighting2.setHeroes(heroes);
        sighting2 = sightingDao.addSighting(sighting2);

        // Retrieve all sightings
        List<Sighting> sightingsFromDao = sightingDao.getAllSightings();

        // Check if the retrieved sightings contain the created sightings
        assertEquals(2, sightingsFromDao.size());
        assertTrue(sightingsFromDao.contains(sighting1));
        assertTrue(sightingsFromDao.contains(sighting2));
    }

    @Test
    @DisplayName("Get and Add Sighting")
    public void testGetAndAddSighting() {
        // Create a test sighting
        Sighting sighting = createSighting();

        // Retrieve the sighting by ID
        Sighting retrievedSighting = sightingDao.getSightingById(sighting.getSightingID());

        // Check if the retrieved sighting matches the original sighting
        assertEquals(sighting, retrievedSighting);
    }

    @Test
    @DisplayName("Update Sighting")
    public void testUpdateSighting() {
        LocalDateTime dateSighted = LocalDateTime.parse("2022-01-01T10:00").withNano(0);

        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower = superpowerDao.addSuperpower(superpower);

        Location location = new Location();
        location.setName("Location Name Test");
        location.setDescription("Location Description Test");
        location.setAddress("Location Address test");
        location.setLatitude("1.1203");
        location.setLongitude("2.345212");
        location = locationDao.addLocation(location);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Sighting sighting = new Sighting();
        sighting.setLocation(location);
        sighting.setSightingDate(dateSighted);
        sighting.setHeroes(heroes);

        // Call DAO addSighting method
        sighting = sightingDao.addSighting(sighting);

        // Call DAO getSightingById method
        Sighting sightingFromDao = sightingDao.getSightingById(sighting.getSightingID());

        // Check if the retrieved sightingFromDao is equal to the sighting
        assertEquals(sighting, sightingFromDao);

        //Now update sighting
        //Add a new hero
        Hero hero2 = new Hero();
        hero2.setName("Batman ");
        hero2.setDescription("Batman description");
        hero2.setOrganizations(new ArrayList<>());
        hero2.setSuperpower(superpower);
        hero2 = heroDao.addHero(hero2);

        List<Hero> newHeroes = new ArrayList<>();
        newHeroes.add(hero);
        newHeroes.add(hero2);

        // Update the sighting date & heroes
        sighting.setHeroes(newHeroes);

        //Update the sighting
        sightingDao.updateSighting(sighting);

        //Should not be equal to the sightingDao retrieved yet
        assertNotEquals(sighting, sightingFromDao, "The Sighting and the SightingFromDao should not yet be equal.");

        //Retrieve the updated sighting
        sightingFromDao = sightingDao.getSightingById(sighting.getSightingID());
        assertEquals(sighting, sightingFromDao);

    }

    @Test
    @DisplayName("Delete Sighting by ID")
    public void testDeleteSightingByID() {
        // Create a test sighting
        Sighting sighting = createSighting();

        // Retrieve the sighting by ID to make sure it is created
        Sighting retrievedSighting = sightingDao.getSightingById(sighting.getSightingID());

        // Check if the retrieved sighting matches the original sighting
        assertEquals(sighting, retrievedSighting);

        // Delete the sighting by ID
        sightingDao.deleteSightingById(sighting.getSightingID());

        // Attempt to retrieve the deleted sighting
        Sighting deletedSighting = sightingDao.getSightingById(sighting.getSightingID());

        // Check if the retrieved sighting is null (indicating it was successfully deleted)
        assertNull(deletedSighting);
    }

    @Test
    @DisplayName("Get Sightings by Date")
    public void testGetSightingsByDate() {
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower = superpowerDao.addSuperpower(superpower);

        Location location = new Location();
        location.setName("Location Name Test");
        location.setDescription("Location Description Test");
        location.setAddress("Location Address test");
        location.setLatitude("1.1203");
        location.setLongitude("2.345212");
        location = locationDao.addLocation(location);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        //Create two sightings with the same date but different times
        LocalDate dateSighted = LocalDate.now();
        LocalDateTime firstSightingDateTime = dateSighted.atTime(2,30,13);
        LocalDateTime secondSightingDateTime = dateSighted.atTime(5,0,0);


        //Create another sighting with a different date
        LocalDateTime differentSightingDate = LocalDateTime.now().plusDays(2);

        Sighting sighting1 = new Sighting();
        sighting1.setLocation(location);
        sighting1.setSightingDate(firstSightingDateTime);
        sighting1.setHeroes(heroes);
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setLocation(location);
        sighting2.setSightingDate(secondSightingDateTime);
        sighting2.setHeroes(heroes);
        sighting2 = sightingDao.addSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setLocation(location);
        sighting3.setSightingDate(differentSightingDate);
        sighting3.setHeroes(heroes);
        sighting3 = sightingDao.addSighting(sighting3);

        // Retrieve all sightings
        List<Sighting> sightingsFromDao = sightingDao.getSightingsByDate(date);

        //Assert the third sighting is not in the retrieved sightings
        assertFalse(sightingsFromDao.contains(sighting3));

        // Check if the retrieved sightings contain only the required sightings
        assertEquals(2, sightingsFromDao.size());
        assertTrue(sightingsFromDao.contains(sighting1));
        assertTrue(sightingsFromDao.contains(sighting2));

    }

    @Test
    @DisplayName("Get All Sightings By Location")
    public void testGetSightingsByLocation() {
        Superpower superpower = new Superpower();
        superpower.setName("Fly");
        superpower = superpowerDao.addSuperpower(superpower);

        //Create the required location
        Location location1 = new Location();
        location1.setName("Location Name Test");
        location1.setDescription("Location Description Test");
        location1.setAddress("Location Address test");
        location1.setLatitude("1.1203");
        location1.setLongitude("2.345212");
        location1 = locationDao.addLocation(location1);

        //Create a different location
        Location location2 = new Location();
        location2.setName("Location Name Test");
        location2.setDescription("Location Description Test");
        location2.setAddress("Location Address test");
        location2.setLatitude("1.1203");
        location2.setLongitude("2.345212");
        location2 = locationDao.addLocation(location2);

        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        //Create 2 sightings with the required location
        Sighting sighting1 = new Sighting();
        sighting1.setLocation(location1);
        sighting1.setSightingDate(date);
        sighting1.setHeroes(heroes);
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setLocation(location1);
        sighting2.setSightingDate(date);
        sighting2.setHeroes(heroes);
        sighting2 = sightingDao.addSighting(sighting2);

        //Create a sighting with a different location
        Sighting sighting3 = new Sighting();
        sighting3.setLocation(location2);
        sighting3.setSightingDate(date);
        sighting3.setHeroes(heroes);
        sighting3 = sightingDao.addSighting(sighting3);

        // Retrieve all sightings
        List<Sighting> sightingsFromDao = sightingDao.getSightingsForLocation(location1);

        //Assert that the third sighting is not retrieved
        assertFalse(sightingsFromDao.contains(sighting3));

        // Check if the retrieved sightings contain the created sightings
        assertEquals(2, sightingsFromDao.size());
        assertTrue(sightingsFromDao.contains(sighting1));
        assertTrue(sightingsFromDao.contains(sighting2));
    }

    @Test
    @DisplayName("Get All Sightings by Hero")
    public void testGetSightingsByHero() {
        // Create test sightings with different heroes
        Sighting sighting1 = createSighting();
        Sighting sighting2 = createSighting();
        Sighting sighting3 = createSighting();

        // Retrieve sightings by a specific hero
        List<Sighting> sightings = sightingDao.getSightingsByHero(sighting1.getHeroes().get(0));

        // Check if the retrieved sightings contain the sightings with the specified hero
        assertTrue(sightings.contains(sighting1));
        assertFalse(sightings.contains(sighting2));
        assertFalse(sightings.contains(sighting3));
    }

    private Sighting createSighting() {
        // Create a superpower
        Superpower superpower = new Superpower();
        superpower.setName("Flying");
        superpower = superpowerDao.addSuperpower(superpower);

        // Create a location
        Location location = new Location();
        location.setName("City Park");
        location.setDescription("A public park in the city");
        location.setAddress("123 Park Street");
        location.setLatitude("40.7128");
        location.setLongitude("-74.0060");
        location = locationDao.addLocation(location);

        // Create a hero
        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Superman description");
        hero.setOrganizations(new ArrayList<>());
        hero.setSuperpower(superpower);
        hero = heroDao.addHero(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        // Create a sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(date);
        sighting.setLocation(location);
        sighting.setHeroes(heroes);

        // Add the sighting to the database
        sighting = sightingDao.addSighting(sighting);

        return sighting;
    }

    private Sighting createSighting(LocalDateTime date) {
        Sighting sighting = createSighting();
        sighting.setSightingDate(date);
        sightingDao.updateSighting(sighting);
        return sighting;
    }
}
