package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HeroDaoDBTest {

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDAO;

    LocalDateTime date = LocalDateTime.now();

    public HeroDaoDBTest() {

    }
    @BeforeEach
    public void setUpClass() {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization org : organizations) {
            organizationDao.deleteOrganizationById(org.getOrganizationID());
        }

        List<Sighting> sightings = sightingDAO.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDAO.deleteSightingById(sighting.getSightingID());
        }

        List<Hero> heroes = heroDao.getAllHeroes();
        for(Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getHeroId());
        }

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperpowerID());
        }
    }
    @Test
    @DisplayName("Get and Add Hero")
    void testGetAndAddHero() {
        //Add new superpower
        Superpower superpower = new Superpower();
        superpower.setName("Test SuperPower");
        superpower = superpowerDao.addSuperpower(superpower);

        //Add Organizations
        Organization organization1 = new Organization();
        organization1.setName("Test Organization1");
        organization1.setAddress("Organization1 Address");
        organization1.setDescription("Organization1 description");
        organization1.setContact("123-123-1232");
        organization1 = organizationDao.addOrganization(organization1);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);

        //Add new hero
        Hero hero = new Hero();
        hero.setName("Test Hero Name");
        hero.setDescription("Test Hero Description");
        hero.setSuperpower(superpower);
        hero.setOrganizations(organizations);

        // Call DAO addHero method
        hero = heroDao.addHero(hero);

        // Call DAO getHeroById method
        Hero heroFromDao = heroDao.getHeroById(hero.getHeroId());
        assertEquals(hero, heroFromDao);
    }

    @Test
    @DisplayName("Get all heroes")
    void testGetAllHeroes() {
        //Create two different superpowers
        Superpower superpower1 = new Superpower();
        superpower1.setName("Test SuperPower1");
        superpower1 = superpowerDao.addSuperpower(superpower1);

        Superpower superpower2 = new Superpower();
        superpower2.setName("Test SuperPower2");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        //Add Organizations
        Organization organization1 = new Organization();
        organization1.setName("Test Organization1");
        organization1.setAddress("Organization1 Address");
        organization1.setDescription("Organization1 description");
        organization1.setContact("123-123-1232");
        organization1 = organizationDao.addOrganization(organization1);

        Organization organization2 = new Organization();
        organization2.setName("Test Organization2");
        organization2.setAddress("Organization2 Address");
        organization2.setDescription("Organization2 description");
        organization2.setContact("123-123-1232");
        organization2 = organizationDao.addOrganization(organization2);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);

        //Create some heroes
        Hero hero1 = new Hero();
        hero1.setName("Test Hero1 Name");
        hero1.setDescription("Test Hero1 Description");
        hero1.setSuperpower(superpower1);
        hero1.setOrganizations(organizations);
        hero1 = heroDao.addHero(hero1);

        Hero hero2 = new Hero();
        hero2.setName("Test Hero2 Name");
        hero2.setDescription("Test Hero2 Description");
        hero2.setSuperpower(superpower1);
        hero2.setOrganizations(organizations);
        hero2 = heroDao.addHero(hero2);

        Hero hero3 = new Hero();
        hero3.setName("Test Hero3 Name");
        hero3.setDescription("Test Hero3 Description");
        hero3.setSuperpower(superpower2);
        hero3.setOrganizations(organizations);
        hero3 = heroDao.addHero(hero3);

        List<Hero> heroesFromDao = heroDao.getAllHeroes();

        assertEquals(3, heroesFromDao.size(), "The DAO should contain 3 heroes.");
        assertTrue(heroesFromDao.contains(hero1), "The DAO should contain hero1");
        assertTrue(heroesFromDao.contains(hero2), "The DAO should contain hero2");
        assertTrue(heroesFromDao.contains(hero3), "The DAO should contain hero3");
    }

    @Test
    @DisplayName("Update Hero")
    void testUpdateHero() {
        //Create new hero
        Superpower superpower = new Superpower();
        superpower.setName("Test SuperPower");
        superpower = superpowerDao.addSuperpower(superpower);

        Hero hero = new Hero();
        hero.setName("Test Hero Name");
        hero.setDescription("Test Hero Description");
        hero.setSuperpower(superpower);
        hero.setOrganizations(new ArrayList<>());
        hero = heroDao.addHero(hero);  //add hero to DAO

        Hero heroFromDao = heroDao.getHeroById(hero.getHeroId()); //get hero from DAO
        assertEquals(hero, heroFromDao);  //assert they are equal

        //Update hero name & description
        hero.setName("Updated Hero Name");
        hero.setDescription("Updated Hero Description");

        heroDao.updateHero(hero); //Call updateHero dao method
        assertNotEquals(hero, heroFromDao);

        heroFromDao = heroDao.getHeroById(hero.getHeroId());
        assertEquals(hero, heroFromDao);
    }

    @Test
    @DisplayName("Delete Hero By ID")
    void testDeleteHeroById() {
        //Create new hero
        Superpower superpower = new Superpower();
        superpower.setName("Test SuperPower");
        superpower = superpowerDao.addSuperpower(superpower);

        //Add Organizations
        Organization organization1 = new Organization();
        organization1.setName("Test Organization1");
        organization1.setAddress("Organization1 Address");
        organization1.setDescription("Organization1 description");
        organization1.setContact("123-123-1232");
        organization1 = organizationDao.addOrganization(organization1);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);

        Hero hero = new Hero();
        hero.setName("Test Hero Name");
        hero.setDescription("Test Hero Description");
        hero.setSuperpower(superpower);
        hero.setOrganizations(organizations);
        hero = heroDao.addHero(hero);  //add hero to DAO

        Hero heroFromDao = heroDao.getHeroById(hero.getHeroId());
        assertEquals(hero, heroFromDao);

        //Delete the hero
        heroDao.deleteHeroById(hero.getHeroId());

        //Try to retrieve the deleted hero
        heroFromDao = heroDao.getHeroById(hero.getHeroId());
        //Assert the hero has been deleted
        assertNull(heroFromDao);
    }

    @Test
    @DisplayName("Get Heroes by Location")
    void testGetHeroesByLocation() {
        //Create superpower
        Superpower superpower1 = new Superpower();
        superpower1.setName("Test SuperPower1");
        superpower1 = superpowerDao.addSuperpower(superpower1);

        //Add a first Organization
        Organization organization = new Organization();
        organization.setName("Test Organization1");
        organization.setAddress("Organization1 Address");
        organization.setDescription("Organization1 description");
        organization.setContact("123-123-1232");
        organization.setHeroes(new ArrayList<>());
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        //Create hero
        Hero hero1 = new Hero();
        hero1.setName("Test Hero1 Name");
        hero1.setDescription("Test Hero1 Description");
        hero1.setSuperpower(superpower1);
        hero1.setOrganizations(new ArrayList<>());
        hero1 = heroDao.addHero(hero1);

        Hero hero2 = new Hero();
        hero2.setName("Test hero2 Name");
        hero2.setDescription("Test hero2 Description");
        hero2.setSuperpower(superpower1);
        hero2.setOrganizations(new ArrayList<>());
        hero2 = heroDao.addHero(hero2);

        List<Hero> heroesForSighting1 = new ArrayList<>();
        heroesForSighting1.add(hero1);
        heroesForSighting1.add(hero2);

        //Create a location
        Location location = new Location();
        location.setName("Downtown Main");
        location.setDescription("Downtown by Main St");
        location.setAddress("123 Main St, Kingsplace, NY");
        location.setLatitude("40.71455");
        location.setLongitude("-74.00712");
        location = locationDao.addLocation(location);

        //Create a sighting
        Sighting sighting1 = new Sighting();
        sighting1.setSightingDate(date);
        sighting1.setHeroes(heroesForSighting1);
        sighting1.setLocation(location);
        sighting1.setHeroes(heroesForSighting1);
        sighting1 = sightingDAO.addSighting(sighting1);

        List<Hero> heroesFromDao = heroDao.getHerosByLocation(location);
        assertEquals(2, heroesFromDao.size());

        assertTrue(heroesFromDao.contains(hero1));
        assertTrue(heroesFromDao.contains(hero2));
    }

    @Test
    @DisplayName("Get Heroes By Organization")
    void testGetHeroesByOrganization() {
        //Create superpower
        Superpower superpower1 = new Superpower();
        superpower1.setName("Test SuperPower1");
        superpower1 = superpowerDao.addSuperpower(superpower1);

        //Add a first Organization
        Organization organization1 = new Organization();
        organization1.setName("Test Organization1");
        organization1.setAddress("Organization1 Address");
        organization1.setDescription("Organization1 description");
        organization1.setContact("123-123-1232");
        organization1 = organizationDao.addOrganization(organization1);

        //Add a second Organization
        Organization organization2 = new Organization();
        organization2.setName("Test Organization2");
        organization2.setAddress("Organization2 Address");
        organization2.setDescription("Test Organization2 description");
        organization2.setContact("123-123-1232");
        organization2 = organizationDao.addOrganization(organization2);

        //Create a list of organization for heroes 1 and 2
        List<Organization> organizationHero1_Hero2 = new ArrayList<>();
        organizationHero1_Hero2.add(organization1);

        //Create another list of organization for hero 3
        List<Organization> organizationHero3 = new ArrayList<>();
        organizationHero3.add(organization2);

        //Create some heroes 1, 2, 3
        Hero hero1 = new Hero();
        hero1.setName("Test Hero1 Name");
        hero1.setDescription("Test Hero1 Description");
        hero1.setSuperpower(superpower1);
        hero1.setOrganizations(organizationHero1_Hero2);
        hero1 = heroDao.addHero(hero1);

        Hero hero2 = new Hero();
        hero2.setName("Test Hero2 Name");
        hero2.setDescription("Test Hero2 Description");
        hero2.setSuperpower(superpower1);
        hero2.setOrganizations(organizationHero1_Hero2);
        hero2 = heroDao.addHero(hero2);

        Hero hero3 = new Hero();
        hero3.setName("Test Hero3 Name");
        hero3.setDescription("Test Hero3 Description");
        hero3.setSuperpower(superpower1);
        hero3.setOrganizations(organizationHero3);
        hero3 = heroDao.addHero(hero3);

        //Retrieve from the DAO the heroes part of organization1
        List<Hero> DAOheroesFromOrganization1 = heroDao.getHerosByOrganization(organization1);
        //Assert only hero1 and hero2 are part of this organization
        assertEquals(2, DAOheroesFromOrganization1.size(), "Only 2 heroes should be in this organization");

        assertTrue(DAOheroesFromOrganization1.contains(hero1), "Hero 1 should be part of the organization");
        assertTrue(DAOheroesFromOrganization1.contains(hero2), "Hero 2 should be part of the organization");

        //Assert that hero 3 does not belong to organization1
        assertFalse(DAOheroesFromOrganization1.contains(hero3), "Hero 3 should be part of a different organization");
    }
}