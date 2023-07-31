package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Organization;

import java.util.List;

public interface HeroDao {
    Hero getHeroById(int heroId);
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int heroId);

    List<Hero> getHerosByLocation(Location location);
    List<Hero> getHerosByOrganization(Organization organization);
}
