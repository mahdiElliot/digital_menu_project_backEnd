package com.example.project.repositories.customer;

import com.example.project.model.customer.Customer;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends EntityRepository<Customer, Long> {
}
