package com.example.project.service.menu;

import com.example.project.model.menu.Menu;
import com.example.project.model.menu.MenuDTO;
import com.example.project.service.IService;

import java.util.List;

public interface IMenuService extends IService<Menu, MenuDTO, Long> {
    List<MenuDTO> findAllByBusinessId(Long id);
}
