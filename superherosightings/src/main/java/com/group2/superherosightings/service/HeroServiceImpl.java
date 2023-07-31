package com.group2.superherosightings.service;

import com.group2.superherosightings.dao.HeroDao;
import com.group2.superherosightings.dao.OrganizationDao;
import com.group2.superherosightings.dao.SuperpowerDao;
import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Location;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.service.HeroExceptions.HeroDuplicateNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author raniaouassif on 2023-07-20
 */
@Service
public class HeroServiceImpl implements HeroServiceInterface {

    @Autowired
    HeroDao heroDao;

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    OrganizationDao organizationDao;

    @Override
    public Hero newHero(Hero hero)  {

        return heroDao.addHero(hero);
    }

    @Override
    public Hero getHeroByID(int heroID) {
        return heroDao.getHeroById(heroID);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }

    @Override
    public void editHero(Hero hero)  {

        heroDao.updateHero(hero);
    }

    @Override
    public void deleteHeroById(int heroId) {
        heroDao.deleteHeroById(heroId);
    }

    @Override
    public List<Hero> getHerosByLocation(Location location) {
        return heroDao.getHerosByLocation(location);
    }

    @Override
    public List<Hero> getHerosByOrganization(Organization organization) {
        return heroDao.getHerosByOrganization(organization);
    }


    //Private helper functions

    /*
     * Iterates over all heroes to see if hero with same name exists
     * Returns true if another hero has the same name
     */
    private boolean checkIfHeroWithSameNameExists(Hero hero) {
        List<Hero> allHeroes = heroDao.getAllHeroes();
        for(Hero h : allHeroes) {
            if(h.getHeroId() == hero.getHeroId()) { //Do not check the same hero
                continue;
            }

            if(h.getName().equals(hero.getName())) {
                return true;
            }
        }

        // No hero with same name exists
        return false;
    }
}
