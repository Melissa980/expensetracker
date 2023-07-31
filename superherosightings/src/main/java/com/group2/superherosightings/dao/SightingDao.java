package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Hero;
import com.group2.superherosightings.dto.Sighting;
import com.group2.superherosightings.dto.Location;

import java.time.LocalDateTime;
import java.util.List;

public interface SightingDao {

    Sighting getSightingById(int sightingID);

    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingById(int id);

    List<Sighting> getSightingsForLocation(Location location);
    List<Sighting> getSightingsByHero(Hero hero);
    List<Sighting> getSightingsByDate(LocalDateTime date);
    List<Sighting> getLatestTenSightings();

    List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected);

    List<Sighting> getAllSightingsByLocation(int locationId);

    List<Sighting> getAllSightingsByLocalDate(LocalDateTime ld);
}
