package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Superpower;

import java.util.List;

public interface SuperpowerDao {

    Superpower getSuperpowerById(int superpowerID);
    List<Superpower> getAllSuperpowers();
    Superpower addSuperpower(Superpower superpower);
    void updateSuperpower(Superpower superpower);
    void deleteSuperpowerById(int superpowerID);
}
