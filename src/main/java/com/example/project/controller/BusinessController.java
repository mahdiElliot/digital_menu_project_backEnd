package com.example.project.controller;

import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import com.example.project.utils.URLUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RequestMapping(URLUtils.BUSINESS)
@Controller
public class BusinessController {
    private final IBusinessService businessService;
    private final ILocationService locationService;

    @Autowired
    public BusinessController(IBusinessService businessService, ILocationService locationService) {
        this.businessService = businessService;
        this.locationService = locationService;
    }

    @Contract(pure = true)
    private @NotNull Function<Long, Location> getLocationFunction() {
        return
                ID -> {
                    LocationDTO locationDTO = locationService.findById(ID);
                    return locationDTO == null ? null : locationDTO.convertToLocationEntity();
                };
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BusinessDTO addBusiness(@Valid @RequestBody BusinessDTO businessDTO) {
        return businessService.save(businessDTO.convertToBusinessEntity(getLocationFunction()));
    }

    @GetMapping
    @ResponseBody
    public List<BusinessDTO> getAllBusinesses() {
        return businessService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public BusinessDTO getBusiness(@PathVariable("id") Long id) {
        return businessService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseBody
    public BusinessDTO deleteBusiness(@PathVariable("id") Long id) {
        return businessService.delete(id);
    }

    @PutMapping(path = "{id}")
    @ResponseBody
    public BusinessDTO updateBusiness(@PathVariable("id") Long id, @RequestBody BusinessDTO businessDTO) {
        businessDTO.setId(id);
        return businessService.save(businessDTO.convertToBusinessEntity(getLocationFunction()));
    }
}
