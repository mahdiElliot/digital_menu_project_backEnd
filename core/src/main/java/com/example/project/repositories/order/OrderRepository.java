package com.example.project.repositories.order;

import com.example.project.model.order.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Modifying
    @Query(value = "DELETE FROM #{#entityName} t WHERE t.id = ?1")
    void delete(Long id);
}
