package com.group2.superherosightings.dao;

import com.group2.superherosightings.dao.Mappers.*;
import com.group2.superherosightings.dto.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SightingDaoDB implements SightingDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDaoDB(
            JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting getSightingById(int sightingID) {
        try {
            final String sql = "SELECT * FROM Sighting WHERE sightingID = ?";
            Sighting sighting = jdbcTemplate.queryForObject(sql, new SightingMapper(), sightingID);

            // Set the location & heroes
            sighting.setLocation(getLocationForSighting(sightingID));
            sighting.setHeroes(getHeroesForSighting(sighting));

            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String sql = "SELECT * FROM Sighting";
        List<Sighting> retrievedSighting = jdbcTemplate.query(sql, new SightingMapper());

        //Set the location & heroes for each sighting
        setLocationAndHeroesForSightingList(retrievedSighting);
        return retrievedSighting;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting (sightingDate, locationID) "
                + "VALUES (?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    INSERT_SIGHTING,
                    Statement.RETURN_GENERATED_KEYS
            );
            LocalDateTime sightingDateTime = sighting.getSightingDate();
            statement.setTimestamp(1, Timestamp.valueOf(sightingDateTime) );
            statement.setInt(2, sighting.getLocation().getLocationID());
            return statement;
        }, keyHolder);

        //Retrieves the generated sighting ID and sets it in the Sighting object
        sighting.setSightingID(keyHolder.getKey().intValue());

        //Insert into the hero sighting into the heroSighting table
        insertHeroSighting(sighting);

        return sighting;
    }



    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET sightingDate = ?, locationID = ? "
                + "WHERE sightingID = ?";
        jdbcTemplate.update(
                UPDATE_SIGHTING,
                Timestamp.valueOf(sighting.getSightingDate()),
                sighting.getLocation().getLocationID(),
                sighting.getSightingID());

        //Update the sighting hero
        final String DELETE_HERO_SIGHTING = "DELETE FROM HeroSighting WHERE sightingID = ?";
        jdbcTemplate.update(DELETE_HERO_SIGHTING, sighting.getSightingID());
        insertHeroSighting(sighting);
    }

    @Override
    public void deleteSightingById(int sightingId) {
        //First delete from the HeroSighting table
        final String DELETE_HERO_SIGHTING = "DELETE FROM HeroSighting WHERE sightingID = ?";
        jdbcTemplate.update(DELETE_HERO_SIGHTING, sightingId);

        //Then delete the sighting
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE sightingID = ?";
        jdbcTemplate.update(DELETE_SIGHTING, sightingId);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDateTime dateTime) {
        try{
            final String GET_SIGHTING_BY_DATE = " SELECT * FROM Sighting WHERE DATE(sightingDate) = ?";

            //Convert the LocalDateTime to LocalDate to retrieve by date only
            LocalDate sightingsDate = dateTime.toLocalDate();

            //Retrieve the sightings based on the sightingDate
            List<Sighting> retrievedSightings = jdbcTemplate.query(GET_SIGHTING_BY_DATE, new SightingMapper(), sightingsDate);

            //Set the location & heroes for each sighting
            setLocationAndHeroesForSightingList(retrievedSightings);

            return retrievedSightings;

        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Sighting> getLatestTenSightings() {
        return null;
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(int locationId) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDateTime ld) {
        return null;
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        try {
            final String sql = "SELECT s.* FROM Sighting s "
                    + "JOIN Location l ON l.locationID = s.locationID "
                    + "WHERE l.locationID = ?";

            List<Sighting> retrievedSightings = jdbcTemplate.query(sql, new SightingMapper(), location.getLocationID());

            //Set the location & heroes for each sighting
            setLocationAndHeroesForSightingList(retrievedSightings);

            return retrievedSightings;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Sighting> getSightingsByHero(Hero hero) {
        try {
            final String sql = "SELECT s.* FROM Sighting s "
                    + "JOIN HeroSighting hs ON hs.sightingID = s.sightingID "
                    + "JOIN Hero h ON h.heroID = hs.heroID "
                    + "WHERE h.heroID = ?";
            List<Sighting> retrievedSightings = jdbcTemplate.query(sql, new SightingMapper(), hero.getHeroId());
            //Set the location & heroes for each sighting
            setLocationAndHeroesForSightingList(retrievedSightings);

            return retrievedSightings;
        } catch (DataAccessException e) {
            return null;
        }

    }

    //Private helper functions :
    private Location getLocationForSighting(int sightingID) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l "
                + "JOIN Sighting s ON s.locationID = l.locationID "
                + "WHERE s.sightingID = ? ";

        return jdbcTemplate.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sightingID);
    }

    private List<Hero> getHeroesForSighting(Sighting sighting) {
        final String SELECT_HEROES_FOR_SIGHTING = "SELECT h.* FROM Hero h "
                + "JOIN HeroSighting hs ON hs.heroID = h.heroID "
                + "JOIN Sighting s ON s.sightingID = hs.sightingID "
                + "WHERE s.sightingID = ?";

        List<Hero> retrievedHeroes = jdbcTemplate.query(
                SELECT_HEROES_FOR_SIGHTING,
                new HeroMapper(),
                sighting.getSightingID()
        );
        for(Hero hero : retrievedHeroes) {
            // Set the superpower & Organizations
            hero.setSuperpower(getSuperpowerForHero(hero.getHeroId()));
            hero.setOrganizations(getOrganizationsForHero(hero));
        }

        return retrievedHeroes;
    }

    private void setLocationAndHeroesForSightingList(List<Sighting> sightingList) {
        for(Sighting sighting : sightingList) {
            int sightingID = sighting.getSightingID();
            // Set the location & heroes
            sighting.setLocation(getLocationForSighting(sightingID));
            sighting.setHeroes(getHeroesForSighting(sighting));
        }
    }


    private void insertHeroSighting(Sighting sighting) {
        if(sighting.getHeroes() != null)  {
            final String INSERT_HERO_SIGHTING = "INSERT INTO "
                    + "HeroSighting(heroID, sightingID) VALUES (?,?);";

            for(Hero hero : sighting.getHeroes()) {
                jdbcTemplate.update(INSERT_HERO_SIGHTING,
                        hero.getHeroId(),
                        sighting.getSightingID());
            }
        }
    }

    // FROM hero
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

        return retrievedOrganizations;
    }
}
