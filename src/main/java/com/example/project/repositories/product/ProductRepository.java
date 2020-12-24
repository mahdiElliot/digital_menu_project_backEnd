package com.example.project.repositories.product;

import com.example.project.model.product.Product;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends EntityRepository<Product, Long> {
}
