package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class BaseController {
    protected final IBusinessService businessService;
    protected final ILocationService locationService;

    @Autowired
    public BaseController(IBusinessService businessService, ILocationService locationService) {
        this.businessService = businessService;
        this.locationService = locationService;
    }

    @Contract(pure = true)
    protected @NotNull Function<Long, Location> locationMapper() {
        return
                ID -> {
                    LocationDTO locationDTO = locationService.findById(ID);
                    return locationDTO == null ? null : locationDTO.convertToLocationEntity();
                };
    }

    @Contract(pure = true)
    protected @NotNull Function<Long, Business> businessMapper() {
        return
                ID -> {
                    BusinessDTO businessDTO = businessService.findById(ID);
                    return businessDTO == null ? null : businessDTO.convertToBusinessEntity(locationMapper());
                };
    }
}
