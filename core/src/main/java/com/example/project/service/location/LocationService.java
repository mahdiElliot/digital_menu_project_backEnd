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
                .map(Location::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public LocationDTO findByName(String name) {
        if (name == null) return new LocationDTO();
        return locationRepository.findByName(name).convertToDTO();
    }

    @Override
    public LocationDTO findById(Long id) {
        if (id == null) return new LocationDTO();
        return locationRepository.findById(id)
                .map(Location::convertToDTO).orElse(new LocationDTO());
    }

    @Override
    public LocationDTO delete(Long id) {
        if (id == null) return new LocationDTO();
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()){
            locationRepository.deleteById(id);
            return location.get().convertToDTO();
        }
        return new LocationDTO();
    }

    @Override
    public LocationDTO save(Location location) {
        return locationRepository.save(location).convertToDTO();
    }
}
