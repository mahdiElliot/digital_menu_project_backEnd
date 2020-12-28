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
                .map(Customer::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findByName(String name) {
        if (name == null) return new CustomerDTO();
        return customerRepository.findByName(name).convertToDTO();
    }

    @Override
    public CustomerDTO findById(Long id) {
        if (id == null) return new CustomerDTO();
        return customerRepository.findById(id)
                .map(Customer::convertToDTO).orElse(new CustomerDTO());
    }

    @Override
    public CustomerDTO delete(Long id) {
        if (id == null) return new CustomerDTO();
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            customerRepository.deleteById(id);
            return customer.get().convertToDTO();
        }
        return new CustomerDTO();
    }

    @Override
    public CustomerDTO save(Customer customer) {
        return customerRepository.save(customer).convertToDTO();
    }
}
