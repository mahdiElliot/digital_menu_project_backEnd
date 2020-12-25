package com.example.project.repositories.product;

import com.example.project.model.product.Product;
import com.example.project.model.suboptions.SubOption;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends EntityRepository<Product, Long> {

    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.category_id = ?1", nativeQuery = true)
    List<Product> findAllByCategoryId(Long id);
}
