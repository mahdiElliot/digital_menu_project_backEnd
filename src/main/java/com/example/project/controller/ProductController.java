package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.location.Location;
import com.example.project.model.product.ProductDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.service.location.ILocationService;
import com.example.project.service.product.IProductService;
import com.example.project.utils.URLUtils;
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

    @PostMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDTO addProduct(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @Valid @RequestBody ProductDTO productDTO) {
        if (businessService.findById(id) != null && categoryService.findById(id2) != null) {
            productDTO.setCategory_id(id2);
            Function<Long, Location> getLocation = ID -> locationService.findById(ID).convertToLocationEntity();
            Function<Long, Business> getBusiness =
                    ID -> businessService.findById(ID).convertToBusinessEntity(getLocation);
            Function<Long, Category> getCategory =
                    ID -> categoryService.findById(ID).convertToCategoryEntity(getBusiness);
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
