package com.example.project.controller;

import com.example.project.model.category.CategoryDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.DataInput;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController extends BaseController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IBusinessService businessService) {
        super(businessService);
        this.categoryService = categoryService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(
            @PathVariable(name = "b_id") Long id,
            @Valid CategoryDTO categoryDTO,
            @RequestParam("photo") MultipartFile multipartFile,
            BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        if (businessService.findById(id) != null) {
            categoryDTO.setBusiness_id(id);
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String uploadDir = URLUtils.BUSINESS + "/" + id + URLUtils.CATEGORY + "/photos/";
            categoryDTO.setImage(uploadDir + fileName);
            CategoryDTO categoryDTO2 = categoryService.save(categoryDTO.convertToCategoryEntity(businessMapper()));
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return categoryDTO2;
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
