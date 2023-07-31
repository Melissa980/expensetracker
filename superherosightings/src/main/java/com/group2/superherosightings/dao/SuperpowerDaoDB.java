package com.group2.superherosightings.dao;

import com.group2.superherosightings.dao.Mappers.SuperpowerMapper;
import com.group2.superherosightings.dto.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SuperpowerDaoDB implements SuperpowerDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Superpower getSuperpowerById(int superpowerID) {

        try {
            final String GET_POWER_BY_ID = "SELECT * FROM Superpower WHERE superpowerID = ?;";

            Superpower superpower = jdbcTemplate.queryForObject(GET_POWER_BY_ID, new SuperpowerMapper(), superpowerID);

            return superpower;
        } catch (DataAccessException ex) {

            return null;

        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String GET_ALL_POWERS = "SELECT * FROM Superpower;";

        return jdbcTemplate.query(GET_ALL_POWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String ADD_POWER = "INSERT INTO Superpower(name) VALUES(?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        //Use JdbcTemplate to execute SQL Statement
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(
                    ADD_POWER,
                    Statement.RETURN_GENERATED_KEYS
            );
            //Sets values for prepared statement
            statement.setString(1, superpower.getName());
            return statement;
        }, keyHolder);

        //Retrieves the generated hero ID and sets it in the Hero object
        superpower.setSuperpowerID(keyHolder.getKey().intValue());

        return superpower;

    }

    @Override
    public void updateSuperpower(Superpower superpower) {



            final String UPDATE_POWER = "UPDATE Superpower SET name = ? WHERE superpowerID = ?;";

            jdbcTemplate.update(UPDATE_POWER,
                    superpower.getName(),
                    superpower.getSuperpowerID());


    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int superpowerID) {

        final String UPDATE_HERO_POWER = "UPDATE hero set superpowerID = NULL WHERE superpowerID = ?";
        jdbcTemplate.update(UPDATE_HERO_POWER, superpowerID);


        final String DELETE_POWER = "DELETE FROM Superpower WHERE superpowerID =?;";

        jdbcTemplate.update(DELETE_POWER, superpowerID);

    }
}
