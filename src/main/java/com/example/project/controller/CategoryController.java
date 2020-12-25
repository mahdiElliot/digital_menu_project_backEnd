package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.location.Location;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.service.location.ILocationService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@Controller
public class CategoryController {
    private final ICategoryService categoryService;
    private final IBusinessService businessService;
    private final ILocationService locationService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IBusinessService businessService, ILocationService locationService) {
        this.categoryService = categoryService;
        this.businessService = businessService;
        this.locationService = locationService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryDTO addCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        if (businessService.findById(id) != null) {
            categoryDTO.setBusiness_id(id);
            Function<Long, Location> getLocation = ID -> locationService.findById(ID).convertToLocationEntity();
            Function<Long, Business> getBusiness =
                    ID -> businessService.findById(ID).convertToBusinessEntity(getLocation);
            return categoryService.save(categoryDTO.convertToCategoryEntity(getBusiness));
        }
        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY)
    @ResponseBody
    public List<CategoryDTO> getAllCategories(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return categoryService.findAllByBusinessId(id);
        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}")
    @ResponseBody
    public CategoryDTO getCategory(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return categoryService.findById(id2);
        return null;
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}")
    @ResponseBody
    public CategoryDTO deleteCategory(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null)
            return categoryService.delete(id2);
        return null;
    }
}
