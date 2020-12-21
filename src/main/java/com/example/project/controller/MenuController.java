package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.menu.MenuDTO;
import com.example.project.service.business.BusinessService;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@Controller
public class MenuController {
    private final IMenuService menuService;
    private final IBusinessService businessService;

    @Autowired
    public MenuController(IMenuService menuService, BusinessService businessService) {
        this.menuService = menuService;
        this.businessService = businessService;
    }

    @PostMapping(path = "/business/{id}/menus")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MenuDTO addMenu(@PathVariable("id") Long id, @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            Function<Long, Business> getBusiness =
                    ID -> businessService.findById(ID).convertToBusinessEntity();
            return menuService.save(menuDTO.convertToMenuEntity(getBusiness));
        }
        return null;
    }

    @GetMapping(path = "/business/{id}/menus")
    @ResponseBody
    public List<MenuDTO> getAllMenus(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return menuService.findAll();
        return null;
    }

    @GetMapping(path = "/business/{id}/menus/{id2}")
    @ResponseBody
    public MenuDTO getMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.findById(id2);
        return null;
    }

    @DeleteMapping(path = "/business/{id}/menus/{id2}")
    @ResponseBody
    public MenuDTO deleteMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.delete(id);
        return null;
    }

    @PutMapping(path = "/business/{id}/menus/{id2}")
    @ResponseBody
    public MenuDTO updateMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            menuDTO.setId(id2);
            Function<Long, Business> getBusiness =
                    businessId -> businessService.findById(businessId).convertToBusinessEntity();
            return menuService.save(menuDTO.convertToMenuEntity(getBusiness));
        }
        return null;
    }
}
