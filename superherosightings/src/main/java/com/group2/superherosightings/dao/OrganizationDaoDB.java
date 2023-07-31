package com.group2.superherosightings.dao;

import com.group2.superherosightings.dao.Mappers.HeroMapper;
import com.group2.superherosightings.dao.Mappers.OrganizationMapper;
import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public Organization getOrganizationById(int organizationId) {
    try {
      final String GET_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE organizationID = ?;";

      Organization organization = jdbcTemplate.queryForObject(GET_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationId);
      organization.setHeroes(getHeroesForOrganization(organization));
      return organization;
    } catch (DataAccessException e) {
      return null;
    }
  }

  @Override
  public List<Organization> getAllOrganizations() {
    final String GET_ALL_ORGS = "SELECT * FROM Organization;";

    List<Organization> organizations = jdbcTemplate.query(GET_ALL_ORGS, new OrganizationMapper());

    // Set heroes for the organization
    setHeroesForOrganizationList(organizations);

    return organizations;
  }

  @Override
  public Organization addOrganization(Organization organization) {
    final String ADD_ORG = "INSERT INTO Organization(name, description, address, contact) VALUES(?, ?, ?, ?);";

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    //Use JdbcTemplate to execute SQL Statement
    jdbcTemplate.update((Connection conn) -> {
      PreparedStatement statement = conn.prepareStatement(
              ADD_ORG,
              Statement.RETURN_GENERATED_KEYS
      );
      //Sets values for prepared statement
      statement.setString(1, organization.getName());
      statement.setString(2, organization.getDescription());
      statement.setString(3, organization.getAddress());
      statement.setString(4, organization.getContact());
      return statement;
    }, keyHolder);

    //Retrieves the generated hero ID and sets it in the Hero object
    organization.setOrganizationID(keyHolder.getKey().intValue());

    //Insert hero organization into the heroOrganization table
    insertHeroOrganization(organization);

    return organization;
  }

  @Override
  public void updateOrganization(Organization organization) {

    final String UPDATE_ORG = "UPDATE Organization SET name = ?, description = ?, address = ?, contact = ? WHERE organizationId = ?;";

    jdbcTemplate.update(UPDATE_ORG,
            organization.getName(),
            organization.getDescription(),
            organization.getAddress(),
            organization.getContact(),
            organization.getOrganizationID());

    //Update the organization hero members
    final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE organizationID = ?";
    jdbcTemplate.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationID());
    insertHeroOrganization(organization);
  }

  @Override
  public void deleteOrganizationById(int organizationId) {
    // First delete from bridge table HeroOrganization
    final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE organizationID = ?";
    jdbcTemplate.update(DELETE_HERO_ORGANIZATION, organizationId);

    //Then delete the organization
    jdbcTemplate.update("DELETE FROM Organization WHERE organizationID=?", organizationId);
  }

  // private helper methods
  private List<Hero> getHeroesForOrganization(Organization organization) {
    final String SELECT_HEROES_FOR_ORGANIZATION = "SELECT h.*, o.name FROM HERO h "
            + "JOIN HeroOrganization ho ON ho.heroID = h.heroID "
            + "JOIN Organization o ON ho.organizationID = o.organizationID "
            + "WHERE o.organizationID = ?";

    List<Hero> retrievedHeroes = jdbcTemplate.query(SELECT_HEROES_FOR_ORGANIZATION, new HeroMapper(), organization.getOrganizationID());

    return retrievedHeroes;
  }

  private void setHeroesForOrganizationList(List<Organization> organizationList) {
    for(Organization org : organizationList) {
      org.setHeroes(getHeroesForOrganization(org));
    }
  }

  private void insertHeroOrganization(Organization org) {
    if(org.getHeroes() != null) {
      final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
              + "HeroOrganization(heroID, organizationID) VALUES (?,?);";
      for(Hero hero  : org.getHeroes()) {
        jdbcTemplate.update(INSERT_HERO_ORGANIZATION,
                hero.getHeroId(),
                org.getOrganizationID());
      }
    }

  }
}
