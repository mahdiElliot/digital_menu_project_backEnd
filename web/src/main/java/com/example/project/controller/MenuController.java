package com.example.project.controller;

import com.example.project.model.menu.MenuDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.menu.IMenuService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController extends BaseController {
    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService, IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
        this.menuService = menuService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDTO addMenu(@PathVariable("id") Long id, @Valid @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            menuDTO.setBusiness_id(id);
            return menuService.save(menuDTO.convertToMenuEntity(businessMapper()));
        }
        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    public List<MenuDTO> getAllMenus(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return menuService.findAllByBusinessId(id);

        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO getMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.findById(id2);
        return null;
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO deleteMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.delete(id2);
        return null;
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO updateMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @Valid @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            menuDTO.setBusiness_id(id);
            menuDTO.setId(id2);
            return menuService.save(menuDTO.convertToMenuEntity(businessMapper()));
        }
        return null;
    }
}
