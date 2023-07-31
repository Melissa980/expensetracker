package com.group2.superherosightings.service;

import com.group2.superherosightings.dao.LocationDao;
import com.group2.superherosightings.dto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationDao locationDao;


    public Location addLocation(Location location) throws LocationDataValidationException {
        validateLocationData(location);
        return locationDao.addLocation(location);
    }

    public List<Location> getAllLocations() {
        return locationDao.getAllLocations();
    }

    public Location getLocationByID(int locationID) {
        return locationDao.getLocationByID(locationID);
    }

    public void updateLocation(int locationID, Location location) {
        locationDao.updateLocation(locationID, location);
    }


    public void deleteLocationByID(int locationID) {
        locationDao.deleteLocationByID(locationID);
    }

    private void validateLocationData(Location location) throws
            LocationDataValidationException {

        // locationID name description address latitude longitude
        if (location.getName() == null
                || location.getName().trim().length() == 0
                || location.getDescription() == null
                || location.getDescription().trim().length() == 0
                || location.getAddress() == null
                || location.getAddress().trim().length() == 0
                || location.getLatitude() == null
                || location.getLatitude().trim().length() == 0
                || location.getLongitude() == null
                || location.getLongitude().trim().length() == 0)
        {

            throw new LocationDataValidationException(
                    "ERROR: All fields [name description address latitude longitude] are required.");
        }

        if (location.getLongitude().length() > 10 || location.getLatitude().length() > 10)
        {
            throw new LocationDataValidationException("Latitude and longitude may not exceed 10 digits.");
        }
    }
}
