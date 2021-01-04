package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.menu.MenuDTO;
import com.example.project.model.menu.MenuDTOReceive;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.menu.IMenuService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController extends BaseController {
    private final IMenuService menuService;

    @Autowired
    public MenuController(IMenuService menuService, IBusinessService businessService) {
        super(businessService);
        this.menuService = menuService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    @ResponseStatus(HttpStatus.CREATED)
    public MenuDTO addMenu(
            @PathVariable("id") Long id,
            @Valid @RequestBody MenuDTOReceive menuDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        BusinessDTO businessDTO = businessService.findById(id);
        if (businessDTO != null) {
            menuDTO.setBusiness_id(id);
            return menuService.save(menuDTO.convertToMenuEntity(businessDTO.convertToBusinessEntity()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU)
    public List<MenuDTO> getAllMenus(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return menuService.findAllByBusinessId(id);

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO getMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null) {
            MenuDTO menuDTO = menuService.findById(id2);
            if (menuDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
            return menuDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO deleteMenu(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null) {
            MenuDTO menuDTO = menuService.delete(id2);
            if (menuDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
            return menuDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @PutMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.MENU + "/{id2}")
    public MenuDTO updateMenu(
            @PathVariable("id") Long id,
            @PathVariable("id2") Long id2,
            @Valid @RequestBody MenuDTOReceive menuDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        BusinessDTO businessDTO = businessService.findById(id);
        if (businessDTO != null) {
            menuDTO.setBusiness_id(id);
            menuDTO.setId(id2);
            return menuService.save(menuDTO.convertToMenuEntity(businessDTO.convertToBusinessEntity()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }
}
