package com.group2.superherosightings.service;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.service.HeroExceptions.HeroDuplicateNameException;

import java.util.List;

/**
 * @author raniaouassif on 2023-07-20
 */
public interface HeroServiceInterface {
    Hero newHero(Hero hero) ;

    Hero getHeroByID(int heroID);

    public List<Hero> getAllHeroes();

    void editHero(Hero hero) ;
    void deleteHeroById(int heroId);

    List<Hero> getHerosByLocation(Location location);
    List<Hero> getHerosByOrganization(Organization organization);
}
