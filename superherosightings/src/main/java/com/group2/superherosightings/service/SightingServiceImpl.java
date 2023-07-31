package com.group2.superherosightings.service;

import com.group2.superherosightings.dao.SightingDao;
import com.group2.superherosightings.dto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SightingServiceImpl implements SightingService {

    private final SightingDao sightingDao;

    @Autowired
    public SightingServiceImpl(SightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @Override
    public Sighting createSighting(Sighting sighting) {
        return null;
    }

    @Override
    public Sighting getSightingById(int sightingId) throws EntityNotFoundException {
        Sighting sighting = sightingDao.getSightingById(sightingId);
        if (sighting == null) {
            throw new EntityNotFoundException("Sighting with ID " + sightingId + " not found.");
        }
        return sighting;
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        return sightingDao.addSighting(sighting);
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }

    @Override
    @Transactional
    public void deleteSightingById(int sightingId) {
        sightingDao.deleteSightingById(sightingId);
    }

    @Override
    public List<Sighting> getLatestTenSightings() {
        return sightingDao.getLatestTenSightings();
    }

    @Override
    public List<Sighting> getSightingsForLocation(int locationId) {
        return null;
    }

    @Override
    public List<Sighting> getAllSightingsForLocation(int locationId) {
        return sightingDao.getAllSightingsByLocation(locationId);
    }

    @Override
    public List<Sighting> getAllSightingsByLocalDate(LocalDateTime ld) {
        return sightingDao.getAllSightingsByLocalDate(ld);
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDateTime dateSelected) {
        return sightingDao.getAllSightingsByDate(dateSelected);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDateTime date) {
        return null;
    }
}

