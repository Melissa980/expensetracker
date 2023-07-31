package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.Location;

import java.util.List;

public interface LocationDao {

    Location addLocation(Location location);

    List<Location> getAllLocations();

    Location getLocationByID(int id);

    void updateLocation(int locationID, Location location);

    void deleteLocationByID(int id);
}