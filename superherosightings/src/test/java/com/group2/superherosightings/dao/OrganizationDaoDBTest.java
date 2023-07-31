package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.*;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationDaoDBTest {

  @Autowired
  LocationDao locationDao;

  @Autowired
  SuperpowerDao powerDao;

  @Autowired
  HeroDao heroDao;

  @Autowired
  SightingDao sightingDao;

  @Autowired
  OrganizationDao organizationDao;

  @BeforeEach
  public void setUpClass() {
    List<Organization> organizations = organizationDao.getAllOrganizations();
    for(Organization org : organizations) {
      organizationDao.deleteOrganizationById(org.getOrganizationID());
    }

    List<Hero> heroes = heroDao.getAllHeroes();
    for(Hero hero : heroes) {
      heroDao.deleteHeroById(hero.getHeroId());
    }

    List<Superpower> superpowers = powerDao.getAllSuperpowers();
    for(Superpower superpower : superpowers) {
      powerDao.deleteSuperpowerById(superpower.getSuperpowerID());
    }

    List<Location> locations = locationDao.getAllLocations();
    locations.forEach(location -> {
      locationDao.deleteLocationByID(location.getLocationID());
    });

    List<Sighting> sightings = sightingDao.getAllSightings();
    sightings.forEach(sighting -> {
      sightingDao.deleteSightingById(sighting.getSightingID());
    });
  }

  @Test
  public void testAddAndGetOrganizationByID() {

    Organization organization = new Organization();
    organization.setName("Federation");
    organization.setDescription("Heros for Justice");
    organization.setAddress("444 Main St, Queensplace, NY");
    organization.setContact("Head Honcho");
    organization.setHeroes(new ArrayList<>());
    organization = organizationDao.addOrganization(organization);

    Organization added = organizationDao.getOrganizationById(organization.getOrganizationID());
    System.out.println(organization);
    System.out.println(added);
    assertEquals(organization, added);
  }

  @Test
  public void getAllOrganizations() {
    Organization organization1 = new Organization();
    organization1.setName("Federation For Good");
    organization1.setDescription("Heros for Justice");
    organization1.setAddress("444 Main St, Queensplace, NY");
    organization1.setContact("Head Good Honcho");
    organization1.setHeroes(new ArrayList<>());
    organization1 = organizationDao.addOrganization(organization1);

    Organization organization2 = new Organization();
    organization2.setName("Federation For Evil");
    organization2.setDescription("Heros for Chaos");
    organization2.setAddress("444 Main St, Jokersplace, NY");
    organization2.setContact("Head Evil Honcho");
    organization2.setHeroes(new ArrayList<>());
    organization2 = organizationDao.addOrganization(organization2);

    List<Organization> organizations = organizationDao.getAllOrganizations();

    assertEquals(2, organizations.size());
    assertTrue(organizations.contains(organization1));
    assertTrue(organizations.contains(organization2));
  }

  @Test
  public void updateOrganization() {
    Organization organization = new Organization();
    organization.setName("Federation");
    organization.setDescription("Heros for Justice");
    organization.setAddress("444 Main St, Queensplace, NY");
    organization.setContact("Head Honcho");
    organization.setHeroes(new ArrayList<>());
    organization = organizationDao.addOrganization(organization);

    Organization updated = organizationDao.getOrganizationById(organization.getOrganizationID());
    assertEquals(organization, updated);

    organization.setDescription("Heros for Justice for All");
    organizationDao.updateOrganization(organization);
    assertNotEquals(organization, updated);

    updated = organizationDao.getOrganizationById(organization.getOrganizationID());
    assertEquals(organization, updated);
  }

  @Test
  public void deleteOrganizationById() {
    Organization organization = new Organization();
    organization.setName("Federation");
    organization.setDescription("Heros for Justice");
    organization.setAddress("444 Main St, Queensplace, NY");
    organization.setContact("Head Honcho");
    organization.setHeroes(new ArrayList<>());
    organization = organizationDao.addOrganization(organization);
    List<Organization> organizations = new ArrayList<>();
    organizations.add(organization);

    Superpower power = new Superpower();
    power.setName("Super Powers");
    power = powerDao.addSuperpower(power);

    Hero hero = new Hero();
    hero.setName("Superman");
    hero.setDescription("From Kyrpton");
    hero.setOrganizations(organizations);
    hero.setSuperpower(power);
    heroDao.addHero(hero);

    organizationDao.deleteOrganizationById(organization.getOrganizationID());

    Organization deleted = organizationDao.getOrganizationById(organization.getOrganizationID());
    assertNull(deleted);
  }
}