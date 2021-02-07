package com.example.project.service.menu;

import com.example.project.model.menu.Menu;
import com.example.project.model.menu.MenuDTO;
import com.example.project.repositories.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuDTO> findAll() {
        return ((List<Menu>) menuRepository.findAll())
                .stream()
                .map(Menu::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MenuDTO findByName(String name) {
        if (name == null) return null;
        return menuRepository.findByName(name)
                .map(Menu::convertToDTO).orElse(null);
    }

    @Override
    public MenuDTO findById(Long id) {
        if (id == null) return null;
        return menuRepository.findById(id)
                .map(Menu::convertToDTO).orElse(null);
    }

    @Override
    public MenuDTO delete(Long id) {
        MenuDTO menuDTO = findById(id);
        if (menuDTO == null) return null;
        menuRepository.delete(id);
        return menuDTO;
    }

    @Override
    public MenuDTO save(Menu menu) {
        return menuRepository.save(menu).convertToDTO();
    }

    @Override
    public List<MenuDTO> findAllByBusinessId(Long id) {
        return ((List<Menu>) menuRepository.findAllByBusinessId(id))
                .stream()
                .map(Menu::convertToDTO).collect(Collectors.toList());
    }
}
