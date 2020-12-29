package com.example.project.controller;

import com.example.project.model.customer.CustomerDTO;
import com.example.project.service.customer.ICustomerService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(URLUtils.CUSTOMER)
@RestController
public class CustomerController {
    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO.convertToCustomerEntity());
    }
}
