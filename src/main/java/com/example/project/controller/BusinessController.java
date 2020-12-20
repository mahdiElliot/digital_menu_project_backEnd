package com.example.project.controller;

import com.example.project.model.DTO;
import com.example.project.model.business.BusinessDTO;
import com.example.project.service.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/business")
@Controller
public class BusinessController {
    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BusinessDTO addBusiness(@RequestBody BusinessDTO businessDTO) {
        return businessService.save(businessDTO.convertToBusinessEntity());
    }

    @GetMapping
    @ResponseBody
    public List<DTO> getAllMenus() {
        return businessService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public DTO getMenu(@PathVariable("id") Long id) {
        return businessService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseBody
    public DTO deleteMenu(@PathVariable("id") Long id) {
        return businessService.delete(id);
    }

    @PutMapping(path = "{id}")
    @ResponseBody
    public BusinessDTO updateBusiness(@PathVariable("id") Long id, @RequestBody BusinessDTO businessDTO) {
        businessDTO.setId(id);
        return businessService.save(businessDTO.convertToBusinessEntity());
    }
}
