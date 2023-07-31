package com.group2.superherosightings.dao.Mappers;

import com.group2.superherosightings.dto.Sighting;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sighting sighting = new Sighting();
        sighting.setSightingID(rs.getInt("sightingID"));

        sighting.setSightingDate(rs.getTimestamp("sightingDate").toLocalDateTime());
        return sighting;
    }
}
