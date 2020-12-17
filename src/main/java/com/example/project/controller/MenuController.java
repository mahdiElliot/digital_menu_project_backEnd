package com.example.project.controller;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.MenuEntity;
import com.example.project.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/menu")
@Controller
public class MenuController {
    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MenuDTO addMenu (@RequestBody MenuDTO menuDTO){
        MenuEntity menuEntity = convertToMenuEntity(menuDTO);
        return menuService.save(menuEntity);
    }

    @GetMapping
    @ResponseBody
    public List<MenuDTO> getAllMenus() {
        return menuService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public MenuDTO getMenu(@PathVariable("id") Long id) {
        return menuService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public MenuDTO deleteMenu(@PathVariable("id") Long id) {
        return menuService.delete(id);
    }

    private MenuEntity convertToMenuEntity(MenuDTO menuDTO) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(menuDTO.getId());
        menuEntity.setName(menuDTO.getName());
        menuEntity.setPickup(menuDTO.getPickup());
        menuEntity.setDelivery(menuDTO.getDelivery());
        menuEntity.setEnabled(menuDTO.getEnabled());
        return menuEntity;
    }
}
