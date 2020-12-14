package com.example.project.service.menu;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.MenuEntity;

import java.util.List;

public interface IMenuService {
    public List<MenuDTO> findAll();

    public MenuDTO findByName(String name);

    public MenuDTO findById(Long id);

    public MenuDTO save(MenuEntity menuEntity);

    public void delete(MenuEntity menuEntity);
}
