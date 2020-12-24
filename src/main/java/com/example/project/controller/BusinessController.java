package com.example.project.controller;

import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.Location;
import com.example.project.service.business.BusinessService;
import com.example.project.service.business.IBusinessService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RequestMapping(URLUtils.BUSINESS)
@Controller
public class BusinessController {
    private final IBusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BusinessDTO addBusiness(@RequestBody BusinessDTO businessDTO) {
        Function<Long, Location> getLocation = id -> null;
        return businessService.save(businessDTO.convertToBusinessEntity(getLocation));
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
        Function<Long, Location> getLocation = ID -> null;
        return businessService.save(businessDTO.convertToBusinessEntity(getLocation));
    }
}
