package com.group2.superherosightings.service;

import com.group2.superherosightings.dto.Location;

import java.util.List;

public interface LocationService {

    Location addLocation(Location location) throws LocationDataValidationException;

    List<Location> getAllLocations();

    Location getLocationByID(int locationID);

    void updateLocation(int locationID, Location location);

    void deleteLocationByID(int locationID);

}
