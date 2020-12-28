package com.example.project.controller;

import com.example.project.model.category.CategoryDTO;
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

@Controller
public class CategoryController extends BaseController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IBusinessService businessService, ILocationService locationService) {
        super(businessService, locationService);
        this.categoryService = categoryService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryDTO addCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        if (businessService.findById(id) != null) {
            categoryDTO.setBusiness_id(id);
            return categoryService.save(categoryDTO.convertToCategoryEntity(getBusinessFunction()));
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
