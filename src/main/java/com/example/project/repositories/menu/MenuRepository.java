package com.example.project.repositories.menu;

import com.example.project.model.menu.Menu;
import com.example.project.repositories.GRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends GRepository<Menu, Long> {
}
