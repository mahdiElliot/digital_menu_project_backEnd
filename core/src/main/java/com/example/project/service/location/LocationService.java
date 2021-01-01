package com.example.project.service.location;

import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.repositories.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationDTO> findAll() {
        return ((List<Location>) locationRepository.findAll())
                .stream().map(Location::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public LocationDTO findByName(String name) {
        return null;
    }

    @Override
    public LocationDTO findById(Long id) {
        if (id == null) return null;
        return locationRepository.findById(id)
                .map(Location::convertToDTO).orElse(null);
    }

    @Override
    public LocationDTO delete(Long id) {
        LocationDTO locationDTO = findById(id);
        if (locationDTO == null) return null;
        locationRepository.deleteById(id);
        return locationDTO;
    }

    @Override
    public LocationDTO save(Location location) {
        return locationRepository.save(location).convertToDTO();
    }
}
