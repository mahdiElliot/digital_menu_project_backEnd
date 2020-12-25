package com.example.project.service.location;

import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.repositories.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }
    @Override
    public List<LocationDTO> findAll() {
        return ((List<Location>) locationRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public LocationDTO findByName(String name) {
        return convertToDTO(locationRepository.findByName(name));
    }

    @Override
    public LocationDTO findById(Long id) {
        return locationRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public LocationDTO delete(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()){
            locationRepository.deleteById(id);
            return convertToDTO(location.get());
        }
        return null;
    }

    @Override
    public LocationDTO save(Location location) {
        return convertToDTO(locationRepository.save(location));
    }

    @Override
    public LocationDTO convertToDTO(Location location) {
        return location.convertToDTO();
    }
}