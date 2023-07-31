package com.group2.superherosightings.dto;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.List;

public class Organization {

  private int organizationID;

  @NotBlank(message = "Organization name must not be empty.")
  @Size(max = 50, message = "Organization name must be less than 50 characters.")
  private String name;

  @Size(max = 100, message = "Description must be less than 100 characters.")
  private String description;

  @Size(max = 100, message = "Address must be less than 100 characters.")
  private String address;

  @Size(max = 100, message = "Contact must be less than 100 characters.")
  private String contact;

  private List<Hero> heroes;

  public int getOrganizationID() {
    return organizationID;
  }

  public void setOrganizationID(int organizationID) {
    this.organizationID = organizationID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public List<Hero> getHeroes() {
    return heroes;
  }

  public void setHeroes(List<Hero> heroes) {
    this.heroes = heroes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Organization)) return false;
    Organization that = (Organization) o;
    return getOrganizationID() == that.getOrganizationID() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getContact(), that.getContact()) && Objects.equals(getHeroes(), that.getHeroes());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOrganizationID(), getName(), getDescription(), getAddress(), getContact(), getHeroes());
  }
}
