package com.group2.superherosightings.dao.Mappers;

import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.dto.Superpower;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class OrganizationMapper implements RowMapper<Organization> {

  @Override
  public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {

    Organization organization = new Organization();

    organization.setOrganizationID(rs.getInt("organizationID"));
    organization.setName(rs.getString("name"));
    organization.setDescription(rs.getString("description"));
    organization.setAddress(rs.getString("address"));
    organization.setContact(rs.getString("contact"));

    return organization;

  }
}
