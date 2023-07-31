package com.group2.superherosightings.dao;

import com.group2.superherosightings.dao.Mappers.HeroMapper;
import com.group2.superherosightings.dao.Mappers.OrganizationMapper;
import com.group2.superherosightings.dao.Mappers.SuperpowerMapper;
import com.group2.superherosightings.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HeroDaoDB implements HeroDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Hero getHeroById(int heroId) {
        try {
            final String GET_HERO_BY_ID = "SELECT * FROM hero WHERE heroID = ? ";
            Hero hero = jdbcTemplate.queryForObject(GET_HERO_BY_ID, new HeroMapper(), heroId);
            // Set the superpower & Organizations
            hero.setSuperpower(getSuperpowerForHero(heroId));
            hero.setOrganizations(getOrganizationsForHero(hero));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String GET_ALL_HEROES = "SELECT * FROM Hero";

        List<Hero> retrievedHeroes = jdbcTemplate.query(GET_ALL_HEROES, new HeroMapper());

        //Set the superpower & organizations for each hero
        setSuperpowerAndOrganizationForHeroList(retrievedHeroes);

        return retrievedHeroes;
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String ADD_HERO = "INSERT INTO Hero(name, description, superpowerID) VALUES(?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        //Use JdbcTemplate to execute SQL Statement
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    ADD_HERO,
                    Statement.RETURN_GENERATED_KEYS
            );
            //Sets values for prepared statement
            statement.setString(1, hero.getName());
            statement.setString(2, hero.getDescription());
            statement.setInt(3, hero.getSuperpower().getSuperpowerID());
            return statement;
        }, keyHolder);

        //Retrieves the generated hero ID and sets it in the Hero object
        hero.setHeroId(keyHolder.getKey().intValue());

        //Insert hero organization into the heroOrganization table
        insertHeroOrganization(hero);

        return hero;
    }

    @Override
    public void updateHero(Hero hero) {

        final String UPDATE_HERO = "UPDATE Hero SET name = ?, description = ?, superpowerID = ? WHERE heroId = ?;";

        jdbcTemplate.update(UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower().getSuperpowerID(),
                hero.getHeroId());

        // Update the hero Organizations
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE heroID = ?";

        jdbcTemplate.update(DELETE_HERO_ORGANIZATION, hero.getHeroId());
        insertHeroOrganization(hero);

    }

    @Override
    @Transactional
    public void deleteHeroById(int heroId) {
        //first delete from bridge table HeroOrganization
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE heroID = ?";
        jdbcTemplate.update(DELETE_HERO_ORGANIZATION, heroId);

        //then delete from bridge table HeroSighting
        final String DELETE_HERO_SIGHTING = "DELETE FROM HeroSighting WHERE heroID = ?";
        jdbcTemplate.update(DELETE_HERO_SIGHTING, heroId);

        //finally delete the hero
        final String DELETE_HERO = "DELETE FROM hero WHERE heroId =?;";

        jdbcTemplate.update(DELETE_HERO, heroId);

    }

    @Override
    public List<Hero> getHerosByLocation(Location location) {
        final String GET_HEROES_BY_LOCATION = "SELECT h.* FROM Hero h " +
                "JOIN HeroSighting hs ON hs.heroID = h.heroID " +
                "JOIN Sighting s ON s.sightingID = hs.sightingID " +
                "JOIN Location l ON s.locationID = l.locationID " +
                "WHERE l.locationID = ?";

        List<Hero> retrievedHeroes = jdbcTemplate.query(GET_HEROES_BY_LOCATION, new HeroMapper(), location.getLocationID());

        //Set the superpower & organizations for each hero
        setSuperpowerAndOrganizationForHeroList(retrievedHeroes);

        return retrievedHeroes;
    }

    @Override
    public List<Hero> getHerosByOrganization(Organization organization) {
        final String GET_HEROES_BY_ORGANIZATION = "SELECT h.* FROM Hero h JOIN "
                + "HeroOrganization ho ON ho.heroID = h.heroID WHERE ho.organizationID = ?";

        List<Hero> retrievedHeroes = jdbcTemplate.query(GET_HEROES_BY_ORGANIZATION, new HeroMapper(), organization.getOrganizationID());

        //Set the superpower & organizations for each hero
        setSuperpowerAndOrganizationForHeroList(retrievedHeroes);

        return retrievedHeroes;
    }


    // PRIVATE HELPER FUNCTIONS
    private Superpower getSuperpowerForHero(int heroID) {
        try {
            final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.* FROM Superpower s " +
                    "JOIN Hero h ON h.superpowerID = s.superpowerID WHERE h.heroID = ?";
            return jdbcTemplate.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerMapper(), heroID);
        } catch(DataAccessException e) {
            return null;
        }

    }

    private List<Organization> getOrganizationsForHero(Hero hero) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = "SELECT o.* FROM Organization o " +
                "JOIN HeroOrganization ho ON o.organizationID = ho.organizationID " +
                "JOIN Hero h ON h.heroID = ho.heroID " +
                "WHERE h.heroID = ?";

        List<Organization> retrievedOrganizations = jdbcTemplate.query(
                SELECT_ORGANIZATIONS_FOR_HERO,
                new OrganizationMapper(),
                hero.getHeroId());

        return retrievedOrganizations.size() == 0 ? new ArrayList<>(): retrievedOrganizations;
    }

    /*
     * Set the superpower & organizations for a list of hero
     * @param List heroes
     */
    private void setSuperpowerAndOrganizationForHeroList(List<Hero> heroes) {
        for(Hero hero : heroes) {
            int heroID = hero.getHeroId();
            hero.setSuperpower(getSuperpowerForHero(heroID));
            hero.setOrganizations(getOrganizationsForHero(hero));
        }
    }

    private void insertHeroOrganization(Hero hero) {
        if(hero.getOrganizations() != null) {
            final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                    + "HeroOrganization(heroID, organizationID) VALUES (?,?);";
            for(Organization organization : hero.getOrganizations()) {
                jdbcTemplate.update(INSERT_HERO_ORGANIZATION,
                        hero.getHeroId(),
                        organization.getOrganizationID());
            }
        }
    }
}
