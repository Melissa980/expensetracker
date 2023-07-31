package com.group2.superherosightings.service;

import com.group2.superherosightings.dao.SuperpowerDao;
import com.group2.superherosightings.dto.Organization;
import com.group2.superherosightings.dto.Superpower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperpowerServiceImpl implements  SuperpowerService{

    @Autowired
    SuperpowerDao superpowerDao;

    public Superpower addSuperpower(Superpower superpower) throws SuperpowerDataValidationException {

        validateSuperpowerInput(superpower);
        return  superpowerDao.addSuperpower(superpower);
    }

    public Superpower getSuperpowerById(int superpowerID) {

        try {
            Superpower superpower = superpowerDao.getSuperpowerById(superpowerID);

            return superpower;
        } catch (DataAccessException e){
            return null;
        }

    }


    public List<Superpower> getAllSuperpowers() {
        return superpowerDao.getAllSuperpowers();
    }

    public void updateSuperpower(Superpower superpower) {

        superpowerDao.updateSuperpower(superpower);
    }

    public void deleteSuperpowerById(int superpowerID) {

        superpowerDao.deleteSuperpowerById(superpowerID);

    }


    private void validateSuperpowerInput(Superpower superpower) throws
            SuperpowerDataValidationException {

        // locationID name description address latitude longitude
        if (superpower.getName() == null
                || superpower.getName().trim().length() == 0) {
            throw new SuperpowerDataValidationException(
                    "ERROR: Name field is required.");
        }
    }
}
