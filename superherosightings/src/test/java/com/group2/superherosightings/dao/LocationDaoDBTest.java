package com.group2.superherosightings.dao;

import com.group2.superherosightings.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocationDaoDBTest {

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDAO;

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    HeroDao heroDao;

    LocalDateTime date = LocalDateTime.now().withNano(0);


    @BeforeEach
    public void setUp() {

        // completely erase the sighting table (it contains Location foreign key)
        List<Sighting> sToBeDeleted = sightingDAO.getAllSightings();
        for (Sighting s : sToBeDeleted) {
            sightingDAO.deleteSightingById(s.getSightingID());
        }

        // completely erase the location table
        List<Location> lToBeDeleted = locationDao.getAllLocations();

        for (Location l : lToBeDeleted) {
            locationDao.deleteLocationByID(l.getLocationID());
        }
    }

    // check basic functionality
    // locationID name description address latitude longitude
    // lines in sightingDao are commented out on line 40 and 46

    @Test
    public void testCreateLocation() {
        Location location = new Location();
        location.setName("Downtown Main");
        location.setDescription("Downtown by Main St");
        location.setAddress("123 Main St, Kingsplace, NY");
        location.setLatitude("40.71455");
        location.setLongitude("-74.00712");
        location = locationDao.addLocation(location);

        Location added = locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, added);
    }

    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setName("Downtown Main");
        location1.setDescription("Downtown by Main St");
        location1.setAddress("123 Main St, Kingsplace, NY");
        location1.setLatitude("40.71455");
        location1.setLongitude("-74.00712");
        location1 = locationDao.addLocation(location1);

        Location location2 = new Location();
        location2.setName("University Main");
        location2.setDescription("Main St by the University");
        location2.setAddress("456 University Ave, Kingsplace, NY");
        location2.setLatitude("41.71465");
        location2.setLongitude("-75.07912");
        location2 = locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdateLocationByID() {
        Location location = new Location();
        location.setName("Downtown Main");
        location.setDescription("Downtown by Main St");
        location.setAddress("123 Main St, Kingsplace, NY");
        location.setLatitude("40.71455");
        location.setLongitude("-74.00712");
        location = locationDao.addLocation(location);

        Location updated = locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, updated);

        location.setName("Main Downtown");
        locationDao.updateLocation(location.getLocationID(), location);
        assertNotEquals(location, updated);

        updated = locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, updated);
    }

    @Test
    public void testDeleteLocationByID () {

        Location location = new Location();
        location.setName("Downtown Main");
        location.setDescription("Downtown by Main St");
        location.setAddress("123 Main St, Kingsplace, NY");
        location.setLatitude("40.71455");
        location.setLongitude("-74.00712");
        location = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(date);
        sighting.setLocation(location);
        sightingDAO.addSighting(sighting);

        locationDao.deleteLocationByID(location.getLocationID());

        List<Sighting> sResult = sightingDAO.getAllSightings();
        List<Location> lResult = locationDao.getAllLocations();

        assertEquals(0, sResult.size());
        assertEquals(0, lResult.size());
    }

}
