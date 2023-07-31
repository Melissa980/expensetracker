package com.group2.superherosightings.dao.Mappers;

import com.group2.superherosightings.dto.Superpower;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SuperpowerMapper implements RowMapper<Superpower> {
    @Override
    public Superpower mapRow(ResultSet rs, int rowNum) throws SQLException {

        Superpower superpower = new Superpower();

        superpower.setSuperpowerID(rs.getInt("superpowerID"));
        superpower.setName(rs.getString("name"));
        return superpower;
    }
}
