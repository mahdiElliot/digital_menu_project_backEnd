package com.example.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface EntityRepository<T, ID> extends CrudRepository<T, ID> {
    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.name=?1", nativeQuery = true)
    T findByName(String name);
}
