package com.group2.superherosightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class Hero {

    private int heroId;
    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be fewer than 50 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 255, message = "Description must be fewer than 255 characters")
    private String description;
    private Superpower superpower;
    private List<Organization> organizations;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }


    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return getHeroId() == hero.getHeroId() && Objects.equals(getName(), hero.getName()) && Objects.equals(getDescription(), hero.getDescription()) && Objects.equals(getSuperpower(), hero.getSuperpower()) && Objects.equals(getOrganizations(), hero.getOrganizations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeroId(), getName(), getDescription(), getSuperpower(), getOrganizations());
    }
}
