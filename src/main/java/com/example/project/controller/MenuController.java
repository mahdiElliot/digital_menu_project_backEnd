package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.menu.MenuDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.menu.IMenuService;
import com.example.project.utils.URLUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@Controller
public class MenuController {
    private final IMenuService menuService;
    private final IBusinessService businessService;
    private final ILocationService locationService;

    @Autowired
    public MenuController(IMenuService menuService, IBusinessService businessService, ILocationService locationService) {
        this.menuService = menuService;
        this.businessService = businessService;
        this.locationService = locationService;
    }


    @Contract(pure = true)
    private @NotNull Function<Long, Location> getLocationFunction() {
        return
                ID -> {
                    LocationDTO locationDTO = locationService.findById(ID);
                    return locationDTO == null ? null : locationDTO.convertToLocationEntity();
                };
    }

    @Contract(pure = true)
    private @NotNull Function<Long, Business> getBusinessFunction() {
        return
                ID -> {
                    BusinessDTO businessDTO = businessService.findById(ID);
                    return businessDTO == null ? null : businessDTO.convertToBusinessEntity(getLocationFunction());
                };
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MenuDTO addMenu(@PathVariable("id") Long id, @Valid @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            menuDTO.setBusiness_id(id);
            return menuService.save(menuDTO.convertToMenuEntity(getBusinessFunction()));
        }
        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    @ResponseBody
    public List<MenuDTO> getAllMenus(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return menuService.findAllByBusinessId(id);

        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    @ResponseBody
    public MenuDTO getMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.findById(id2);
        return null;
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    @ResponseBody
    public MenuDTO deleteMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return menuService.delete(id2);
        return null;
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    @ResponseBody
    public MenuDTO updateMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @Valid @RequestBody MenuDTO menuDTO) {
        if (businessService.findById(id) != null) {
            menuDTO.setBusiness_id(id);
            menuDTO.setId(id2);
            return menuService.save(menuDTO.convertToMenuEntity(getBusinessFunction()));
        }
        return null;
    }
}
