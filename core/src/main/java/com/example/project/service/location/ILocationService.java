package com.example.project.service.location;

import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.service.IService;

import java.util.List;

public interface ILocationService extends IService<Location, LocationDTO, Long> {
    List<LocationDTO> findNearests(LocationDTO locationDTO, double distance);
}
