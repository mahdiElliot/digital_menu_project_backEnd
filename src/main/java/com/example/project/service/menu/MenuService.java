package com.example.project.service.menu;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.MenuEntity;
import com.example.project.repository.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuDTO> findAll() {
        return ((List<MenuEntity>) menuRepository.findAll())
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MenuDTO findByName(String name) {
        return convertToDTO(menuRepository.findByName(name));
    }

    @Override
    public MenuDTO findById(Long id) {
        if(menuRepository.findById(id).isPresent() ||
                menuRepository.findById(id).isEmpty())
            return convertToDTO(menuRepository.findById(id).get());

        return null;
    }

    @Override
    public MenuDTO save(MenuEntity menuEntity) {
        return convertToDTO(menuRepository.save(menuEntity));
    }

    @Override
    public void delete(MenuEntity menuEntity) {
        menuRepository.delete(menuEntity);
    }

    private MenuDTO convertToDTO(MenuEntity menuEntity){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menuEntity.getId());
        menuDTO.setDelivery(menuEntity.getDelivery());
        menuDTO.setEnabled(menuEntity.getEnabled());
        menuDTO.setName(menuEntity.getName());
        menuDTO.setPickup(menuEntity.getPickup());
        return menuDTO;
    }
}
