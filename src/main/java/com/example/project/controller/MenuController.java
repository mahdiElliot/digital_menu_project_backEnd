package com.example.project.controller;

import com.example.project.model.DTO;
import com.example.project.model.business.Business;
import com.example.project.model.menu.MenuDTO;
import com.example.project.service.business.BusinessService;
import com.example.project.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RequestMapping("/menu")
@Controller
public class MenuController {
    private final IMenuService menuService;
    private final BusinessService businessService;

    @Autowired
    public MenuController(IMenuService menuService, BusinessService businessService) {
        this.menuService = menuService;
        this.businessService = businessService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MenuDTO addMenu (@RequestBody MenuDTO menuDTO) {
        Function<Long, Business> getBusiness =
                id -> businessService.findById(id).convertToBusinessEntity();
        return menuService.save(menuDTO.convertToMenuEntity(getBusiness));
    }

    @GetMapping
    @ResponseBody
    public List<DTO> getAllMenus() {
        return menuService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public DTO getMenu(@PathVariable("id") Long id) {
        return menuService.findById(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseBody
    public DTO deleteMenu(@PathVariable("id") Long id) {
        return menuService.delete(id);
    }

    @PutMapping(path = "{id}")
    @ResponseBody
    public MenuDTO updateMenu(@PathVariable("id") Long id, @RequestBody MenuDTO menuDTO) {
        menuDTO.setId(id);
        Function<Long, Business> getBusiness =
                businessId -> businessService.findById(businessId).convertToBusinessEntity();
        return menuService.save(menuDTO.convertToMenuEntity(getBusiness));
    }
}
