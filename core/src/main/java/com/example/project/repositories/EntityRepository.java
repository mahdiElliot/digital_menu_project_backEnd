package com.example.project.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityRepository<T, ID> extends CrudRepository<T, ID> {
    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.name=?1", nativeQuery = true)
    Optional<T> findByName(String name);

    @Modifying
    @Query(value = "DELETE FROM #{#entityName} t WHERE t.id = ?1")
    void delete(Long id);
}
