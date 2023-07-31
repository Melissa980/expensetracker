package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.dto.Superpower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SuperpowerDaoDBTest {

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    HeroDao heroDao;

    @Autowired
    OrganizationDao organizationDao;

    public SuperpowerDaoDBTest(){

    }

    @BeforeEach
    public void setUp() {

        List<Superpower> powers = superpowerDao.getAllSuperpowers();
        powers.forEach(power -> {
            superpowerDao.deleteSuperpowerById(power.getSuperpowerID());
        });

        List<Hero> heros = heroDao.getAllHeroes();
        heros.forEach(hero -> {
            heroDao.deleteHeroById(hero.getHeroId());
        });

        List<Organization> organizations = organizationDao.getAllOrganizations();
        organizations.forEach(organization -> {
            organizationDao.deleteOrganizationById(organization.getOrganizationID());
        });

    }

    @Test
    public void testAddAndGetSuperpowerByID() {
        Superpower power = new Superpower();
        power.setName("Spidey Senses");
        power = superpowerDao.addSuperpower(power);

        Superpower addedPower = superpowerDao.getSuperpowerById(power.getSuperpowerID());
        assertEquals(power, addedPower);
    }


    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("Super Strength");
        superpower1 = superpowerDao.addSuperpower(superpower1);

        Superpower superpower2 = new Superpower();
        superpower2.setName("Telepathy");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        Superpower superpower3 = new Superpower();
        superpower3.setName("Flying");
        superpower3 = superpowerDao.addSuperpower(superpower3);

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();

        assertEquals(3, superpowers.size());
        assertTrue(superpowers.contains(superpower1));
        assertTrue(superpowers.contains(superpower2));
        assertTrue(superpowers.contains(superpower3));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("Super Strength");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower updatedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerID());
        assertEquals(superpower, updatedSuperpower);

        superpower.setName("Super Durability");
        superpowerDao.updateSuperpower(superpower);
        assertNotEquals(superpower, updatedSuperpower);

        updatedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerID());
        assertEquals(superpower, updatedSuperpower);
    }

    @Test
    public void testDeletePowerByID() {
        Superpower superpower = new Superpower();
        superpower.setName("Teleport");
        superpower = superpowerDao.addSuperpower(superpower);

        Organization organization = new Organization();
        organization.setName("DC");
        organization.setDescription("Saviors of the Universe");
        organization.setAddress("123 37th Street, New York, NY");
        organization.setContact("000-000-0000");
        organization.setHeroes(new ArrayList<>());
        organization = organizationDao.addOrganization(organization);

        List <Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setName("Wonder Woman");
        hero.setDescription("Amazon warrior");
        hero.setSuperpower(superpower);
        hero.setOrganizations(organizations);
        hero = heroDao.addHero(hero);


      Superpower deletedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerID());

      assertEquals(superpower, deletedSuperpower);



      superpowerDao.deleteSuperpowerById(superpower.getSuperpowerID());

     deletedSuperpower = superpowerDao.getSuperpowerById(superpower.getSuperpowerID());
     assertNull(deletedSuperpower);

     //TODO: Look into NullPointer Exception

//       hero = heroDao.getHeroById(hero.getHeroId());
//       assertNull(hero.getSuperpower());

    }


}
