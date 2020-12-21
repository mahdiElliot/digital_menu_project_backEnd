package com.example.project.repositories.menu;

import com.example.project.model.menu.Menu;
import com.example.project.repositories.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends EntityRepository<Menu, Long> {
}
