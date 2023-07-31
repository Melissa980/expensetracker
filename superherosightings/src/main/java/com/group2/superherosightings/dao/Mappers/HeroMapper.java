package com.group2.superherosightings.dao.Mappers;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Superpower;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class HeroMapper implements RowMapper<Hero> {

    @Override
    public Hero mapRow(ResultSet rs, int index) throws SQLException {
        Hero hero = new Hero();
        hero.setHeroId(rs.getInt("heroId"));
        hero.setName(rs.getString("name"));
        hero.setDescription(rs.getString("description"));

        return hero;
    }
}
