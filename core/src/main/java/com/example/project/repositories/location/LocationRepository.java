package com.example.project.repositories.location;

import com.example.project.model.location.Location;

import java.util.Optional;

public interface LocationRepository {
    Iterable<Location> findAll();

    Optional<Location> findById(Long id);

    void deleteById(Long id);

    Location save(Location location);
}
