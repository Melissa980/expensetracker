package com.group2.superherosightings.service;

import com.group2.superherosightings.dao.OrganizationDao;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

  @Autowired
  OrganizationDao organizationDao;

  @Override
  public Organization addOrganization(Organization organization) throws OrganizationDataValidationException {
    validateOrganizationData(organization);
    return organizationDao.addOrganization(organization);
  }

  @Override
  public List<Organization> getAllOrganization() {
    return organizationDao.getAllOrganizations();
  }

  @Override
  public Organization getOrganizationByID(int organizationID) {
    try {
      return organizationDao.getOrganizationById(organizationID);
    }
    catch (DataAccessException e){
      return null;
    }
  }

  @Override
  public void updateOrganization(Organization organization) {
    organizationDao.updateOrganization(organization);
  }

  @Override
  public void deleteOrganizationByID(int organizationID) {
    organizationDao.deleteOrganizationById(organizationID);
  }

  private void validateOrganizationData(Organization organization) throws
          OrganizationDataValidationException {

    // locationID name description address latitude longitude
    if (organization.getName() == null
            || organization.getName().trim().length() == 0
            || organization.getHeroes() == null) {
      throw new OrganizationDataValidationException(
              "ERROR: Name and heroes field is required.");
    }
  }
}
