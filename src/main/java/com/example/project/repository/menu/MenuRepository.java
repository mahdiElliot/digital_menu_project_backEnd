package com.example.project.repository.menu;

import com.example.project.model.menu.MenuEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, Long> {
    
    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.name=?1", nativeQuery = true)
    MenuEntity findByName(String name);
}
