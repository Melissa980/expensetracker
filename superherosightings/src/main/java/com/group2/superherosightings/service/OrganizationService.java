package com.group2.superherosightings.service;

import com.group2.superherosightings.dto.Organization;

import java.util.List;

public interface OrganizationService {

  Organization addOrganization(Organization organization) throws OrganizationDataValidationException;

  List<Organization> getAllOrganization();

  Organization getOrganizationByID(int organizationID);

  void updateOrganization(Organization organization);

  void deleteOrganizationByID(int organizationID);
}
