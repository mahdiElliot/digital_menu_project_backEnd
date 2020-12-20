package com.example.project.repositories.menu;

import com.example.project.model.menu.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    @Query(value = "SELECT * FROM #{#entityName} t WHERE t.name=?1", nativeQuery = true)
    Menu findByName(String name);
}
