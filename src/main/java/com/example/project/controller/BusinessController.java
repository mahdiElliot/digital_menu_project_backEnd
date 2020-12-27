package com.example.project.controller;

import com.example.project.model.business.BusinessDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import com.example.project.utils.URLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(URLUtils.BUSINESS)
@Controller
public class BusinessController extends BaseController {

    @Autowired
    public BusinessController(IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
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
