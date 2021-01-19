package com.example.project.repositories.customer;

import com.example.project.model.customer.Customer;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends EntityRepository<Customer, Long> {
    @Query(value = "SELECT t.id, t.name, t.email, t.phone_number FROM #{#entityName} t FULL JOIN corder ON t.id = corder.customer_id WHERE business_id = ?1", nativeQuery = true)
    Iterable<Customer> findCustomersOrderedInBusiness(Long businessId);

    @Query(value = "SELECT t.id, t.name, t.email, t.phone_number FROM #{#entityName} t FULL JOIN corder ON t.id = corder.customer_id WHERE business_id = ?1 AND table_number = ?2", nativeQuery = true)
    Iterable<Customer> findCustomersOrderedInBusinessByTableNumber(Long businessId, Integer tableNumber);
}
