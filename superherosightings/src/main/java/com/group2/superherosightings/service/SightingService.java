package com.group2.superherosightings.service;

import com.group2.superherosightings.dto.Sighting;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface SightingService {

    Sighting createSighting(@NotNull Sighting sighting);

    Sighting getSightingById(int sightingId) throws  EntityNotFoundException;

    List<Sighting> getAllSightings();

    void updateSighting(@NotNull Sighting sighting);

    void deleteSightingById(int sightingId);

    List<Sighting> getLatestTenSightings();

    List<Sighting> getSightingsForLocation(int locationId);

    List<Sighting> getAllSightingsForLocation(int locationId);

    List<Sighting> getAllSightingsByLocalDate(LocalDateTime ld);

    List<Sighting> getAllSightingsByDate(@NotNull LocalDateTime dateSelected);

    List<Sighting> getSightingsByDate(@NotNull LocalDateTime date);

    Sighting addSighting(Sighting sighting);

}

