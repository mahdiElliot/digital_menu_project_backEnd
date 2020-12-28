package com.example.project.repositories.menu;

import com.example.project.model.menu.Menu;
import com.example.project.repositories.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends EntityRepository<Menu, Long> {

    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.business_id = ?1", nativeQuery = true)
    List<Menu> findAllByBusinessId(Long id);
}
