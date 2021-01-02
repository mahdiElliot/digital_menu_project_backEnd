package com.example.project.controller;

import com.example.project.model.category.CategoryDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController extends BaseController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IBusinessService businessService) {
        super(businessService);
        this.categoryService = categoryService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(@PathVariable("id") Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        if (businessService.findById(id) != null) {
            categoryDTO.setBusiness_id(id);
            return categoryService.save(categoryDTO.convertToCategoryEntity(businessMapper()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY)
    public List<CategoryDTO> getAllCategories(@PathVariable("id") Long id) {
        if (businessService.findById(id) != null)
            return categoryService.findAllByBusinessId(id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}")
    public CategoryDTO getCategory(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null) {
            CategoryDTO categoryDTO = categoryService.findById(id2);
            if (categoryDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
            return categoryDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}")
    public CategoryDTO deleteCategory(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null) {
            CategoryDTO categoryDTO = categoryService.delete(id2);
            if (categoryDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
            return categoryDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business " + ErrorUtils.NOT_FOUND);
    }
}
