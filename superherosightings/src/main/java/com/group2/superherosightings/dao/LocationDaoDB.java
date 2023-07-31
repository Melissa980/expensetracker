package com.group2.superherosightings.dao;

import com.group2.superherosightings.dao.Mappers.LocationMapper;
import com.group2.superherosightings.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Location addLocation(Location location) {

        String CREATE_LOCATION = "INSERT INTO Location (name, description, address, latitude, longitude) VALUES (?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    CREATE_LOCATION,
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, location.getName());
            statement.setString(2, location.getDescription());
            statement.setString(3, location.getAddress());
            statement.setString(4, location.getLatitude());
            statement.setString(5, location.getLongitude());
            return statement;
        }, keyHolder);

        location.setLocationID(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public List<Location> getAllLocations() {

        String GET_ALL_LOCATIONS = "SELECT * FROM Location";

        return jdbcTemplate.query(GET_ALL_LOCATIONS, new LocationMapper());

    }

    @Override
    public Location getLocationByID(int id) {

        String GET_LOCATION_BY_ID = "SELECT * FROM Location WHERE locationID=?";

        return jdbcTemplate.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);

    }

    @Override
    public void updateLocation(int locationID, Location location) {
        // locationID name description address latitude longitude

        String UPDATE_LOCATION = "UPDATE Location SET name=?, description=?, address=?, latitude=?, longitude=? " +
                "WHERE locationID=?";

        jdbcTemplate.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationID()
        );
    }

    @Override
    public void deleteLocationByID(int id) {
//        jdbcTemplate.update("DELETE FROM HeroSighting WHERE locationID=?", id);

        jdbcTemplate.update("DELETE FROM Sighting WHERE locationID=?", id);
        jdbcTemplate.update("DELETE FROM Location WHERE locationID=?", id);
    }
}
