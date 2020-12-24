package com.example.project.service.menu;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.Menu;
import com.example.project.repositories.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MenuDTO findByName(String name) {
        return convertToDTO(menuRepository.findByName(name));
    }

    @Override
    public MenuDTO findById(Long id) {
        return menuRepository.findById(id)
                .map(this::convertToDTO).orElse(null);
    }

    @Override
    public MenuDTO delete(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            menuRepository.deleteById(id);
            return convertToDTO(menu.get());
        }
        return null;
    }

    @Override
    public MenuDTO save(Menu menu) {
        return convertToDTO(menuRepository.save(menu));
    }

    @Override
    public List<MenuDTO> findAllByBusinessId(Long id) {
        return (menuRepository.findAllByBusinessId(id))
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MenuDTO convertToDTO(Menu menu) {
        return menu.convertToDTO();
    }

}
