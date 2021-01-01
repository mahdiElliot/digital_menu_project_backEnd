package com.example.project.repositories.category;

import com.example.project.model.category.Category;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends EntityRepository<Category, Long> {
    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.business_id = ?1", nativeQuery = true)
    List<Category> findAllByBusinessId(Long id);
}
