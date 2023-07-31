package com.group2.superherosightings.service;

import com.group2.superherosightings.dto.Superpower;

import java.util.List;

public interface SuperpowerService {

    Superpower addSuperpower(Superpower superpower) throws SuperpowerDataValidationException;
    Superpower getSuperpowerById(int superpowerID);
    public List<Superpower> getAllSuperpowers();

    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerById(int superpowerID);
}
