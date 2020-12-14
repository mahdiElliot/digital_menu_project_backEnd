package com.example.project.controller;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.MenuEntity;
import com.example.project.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @PostMapping(path = "/menu/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MenuDTO addMenu (@RequestBody MenuDTO menuDTO){
        MenuEntity menuEntity = convertToMenuEntity(menuDTO);
        return menuService.save(menuEntity);
    }

    @GetMapping(path = "/menu/all")
    @ResponseBody
    public List<MenuDTO> getAllMenus(){
        return menuService.findAll();
    }

    @GetMapping("/menu/{id}")
    public MenuDTO getMenu(@PathVariable("id") Long id){
        return menuService.findById(id);
    }

    private MenuEntity convertToMenuEntity(MenuDTO menuDTO){
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(menuDTO.getId());
        menuEntity.setName(menuDTO.getName());
        menuEntity.setPickup(menuDTO.getPickup());
        menuEntity.setDelivery(menuDTO.getDelivery());
        menuEntity.setEnabled(menuDTO.getEnabled());
        return menuEntity;
    }
}
