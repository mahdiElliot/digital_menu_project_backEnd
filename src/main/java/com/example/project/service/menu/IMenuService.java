package com.example.project.service.menu;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.Menu;

import java.util.List;

public interface IMenuService {
    public List<MenuDTO> findAll();

    public MenuDTO findByName(String name);

    public MenuDTO findById(Long id);

    public MenuDTO save(Menu menu);

    public MenuDTO delete(Long id);
}
