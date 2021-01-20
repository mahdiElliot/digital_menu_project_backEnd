package com.example.project.repositories.purchase;

import com.example.project.model.purchase.Purchase;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends EntityRepository<Purchase, Long> {
}
