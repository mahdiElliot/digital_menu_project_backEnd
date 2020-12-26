package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.location.Location;
import com.example.project.model.location.LocationDTO;
import com.example.project.model.product.ProductDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.product.IProductService;
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
public class ProductController {
    private final IProductService productService;
    private final IBusinessService businessService;
    private final ICategoryService categoryService;
    private final ILocationService locationService;

    @Autowired
    public ProductController(IProductService productService, IBusinessService businessService,
                             ICategoryService categoryService, ILocationService locationService) {
        this.productService = productService;
        this.businessService = businessService;
        this.categoryService = categoryService;
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

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDTO addProduct(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @Valid @RequestBody ProductDTO productDTO) {
        if (businessService.findById(id) != null && categoryService.findById(id2) != null) {
            productDTO.setCategory_id(id2);
            Function<Long, Category> getCategory =
                    ID -> {
                        CategoryDTO categoryDTO = categoryService.findById(ID);
                        return categoryDTO == null ? null : categoryDTO.convertToCategoryEntity(getBusinessFunction());
                    };
            return productService.save(productDTO.convertToProductEntity(getCategory));
        }
        return null;
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT)
    @ResponseBody
    public List<ProductDTO> getAllProducts(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null && categoryService.findById(id) != null)
            return productService.findAllByCategoryId(id2);

        return null;
    }

}