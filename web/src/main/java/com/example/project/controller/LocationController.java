package com.example.project.controller;

import com.example.project.model.location.LocationDTO;
import com.example.project.service.location.ILocationService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(URLUtils.LOCATION)
@RestController
public class LocationController {
    private final ILocationService locationService;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO) {
        return locationService.save(locationDTO.convertToLocationEntity());
    }
}
