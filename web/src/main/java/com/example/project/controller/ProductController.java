package com.example.project.controller;

import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
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
public class ProductController extends BaseController {
    private final IProductService productService;
    private final ICategoryService categoryService;

    @Autowired
    public ProductController(IProductService productService, IBusinessService businessService,
                             ICategoryService categoryService, ILocationService locationService) {
        super(businessService, locationService);
        this.productService = productService;
        this.categoryService = categoryService;
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
