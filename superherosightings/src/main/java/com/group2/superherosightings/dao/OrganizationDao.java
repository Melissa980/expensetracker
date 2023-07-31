package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Organization;

import java.util.List;

public interface OrganizationDao {

  Organization getOrganizationById(int organizationId);
  List<Organization> getAllOrganizations();
  Organization addOrganization(Organization organization);
  void updateOrganization(Organization organization);
  void deleteOrganizationById(int organizationId);
}
