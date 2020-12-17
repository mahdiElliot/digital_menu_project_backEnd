package com.example.project.controller;

import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.Menu;
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
    public MenuDTO addMenu (@RequestBody MenuDTO menuDTO) {
        Menu menu = convertToMenuEntity(menuDTO);
        return menuService.save(menu);
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
    @ResponseBody
    public MenuDTO deleteMenu(@PathVariable("id") Long id) {
        return menuService.delete(id);
    }

    @PutMapping(path = "{id}")
    @ResponseBody
    public MenuDTO updateMenu(@PathVariable("id") Long id, @RequestBody MenuDTO menuDTO) {
        Menu menu = convertToMenuEntity(menuDTO);
        return menuService.update(id, menu);
    }

    private Menu convertToMenuEntity(MenuDTO menuDTO) {
        Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        menu.setName(menuDTO.getName());
        menu.setPickup(menuDTO.getPickup());
        menu.setDelivery(menuDTO.getDelivery());
        menu.setEnabled(menuDTO.getEnabled());
        return menu;
    }
}
