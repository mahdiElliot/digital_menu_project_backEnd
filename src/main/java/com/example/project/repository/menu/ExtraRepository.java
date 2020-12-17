package com.example.project.repository.menu;

import com.example.project.model.menu.Menu;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraRepository {
    public Optional<Menu> update(Long id, Menu menu);
}
