package com.example.project.service.customer;

import com.example.project.model.customer.Customer;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.service.IService;

import java.util.List;

public interface ICustomerService extends IService<Customer, CustomerDTO, Long> {
    List<CustomerDTO> findCustomersOrderedInBusiness(Long businessId);

    List<CustomerDTO> findCustomersOrderedInBusinessByTableNumber(Long businessId, Integer tableNumber);
}
