package com.example.project.service.customer;

import com.example.project.model.customer.Customer;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.repositories.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return ((List<Customer>) customerRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByName(String name) {
        return convertToDTO(customerRepository.findByName(name));
    }

    @Override
    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public CustomerDTO delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            customerRepository.deleteById(id);
            return convertToDTO(customer.get());
        }
        return null;
    }

    @Override
    public CustomerDTO save(Customer customer) {
        return convertToDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO convertToDTO(Customer customer) {
        return customer.convertToDTO();
    }
}
