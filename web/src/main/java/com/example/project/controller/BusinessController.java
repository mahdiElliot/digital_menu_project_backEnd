package com.example.project.controller;

import com.example.project.model.business.BusinessDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.URLUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequestMapping(URLUtils.BUSINESS)
@RestController
public class BusinessController extends BaseController {

    @Autowired
    public BusinessController(IBusinessService businessService) {
        super(businessService);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessDTO addBusiness(@Valid @RequestBody BusinessDTO businessDTO) {
        return businessService.save(businessDTO.convertToBusinessEntity());
    }

    @GetMapping
    public List<BusinessDTO> getAllBusinesses() {
        return businessService.findAll();
    }

    @GetMapping(path = "{id}")
    public BusinessDTO getBusiness(@PathVariable("id") Long id) {
        BusinessDTO businessDTO = businessService.findById(id);
        if (businessDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
        return businessDTO;
    }

    @DeleteMapping(path = "{id}")
    public BusinessDTO deleteBusiness(@PathVariable("id") Long id) {
        BusinessDTO businessDTO = businessService.delete(id);
        if (businessDTO == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_DELETED);
        return businessDTO;
    }

    @PutMapping(path = "{id}")
    public BusinessDTO updateBusiness(@PathVariable("id") Long id, @Valid @RequestBody BusinessDTO businessDTO) {
        businessDTO.setId(id);
        return businessService.save(businessDTO.convertToBusinessEntity());
    }
}
