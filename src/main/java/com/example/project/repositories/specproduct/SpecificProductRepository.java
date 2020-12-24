package com.example.project.repositories.specproduct;

import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificProductRepository extends EntityRepository<SpecificProduct, Long> {
}
