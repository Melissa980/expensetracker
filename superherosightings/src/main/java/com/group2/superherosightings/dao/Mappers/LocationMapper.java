package com.group2.superherosightings.dao.Mappers;

import com.group2.superherosightings.dto.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        // locationID name description address latitude longitude

        Location location = new Location();
        location.setLocationID(rs.getInt("locationID"));
        location.setName(rs.getString("name"));
        location.setDescription(rs.getString("description"));
        location.setAddress(rs.getString("address"));
        location.setLatitude(rs.getString("latitude"));
        location.setLongitude(rs.getString("longitude"));

        return location;
    }

}
