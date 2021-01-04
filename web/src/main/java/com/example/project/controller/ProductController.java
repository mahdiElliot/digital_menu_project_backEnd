package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.product.ProductDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.service.product.IProductService;
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
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class ProductController extends BaseController {
    private final IProductService productService;
    private final ICategoryService categoryService;

    @Autowired
    public ProductController(IProductService productService, IBusinessService businessService,
                             ICategoryService categoryService) {
        super(businessService);
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping(path = URLUtils.BUSINESS + "/{b_id}" + URLUtils.CATEGORY + "/{c_id}" + URLUtils.PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(
            @PathVariable("b_id") Long id,
            @PathVariable("c_id") Long id2,
            @Valid ProductDTO productDTO,
            @RequestParam("photo") MultipartFile multipartFile,
            BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        BusinessDTO businessDTO = businessService.findById(id);
        if (businessDTO != null && categoryService.findById(id2) != null) {
            productDTO.setCategory_id(id2);
            Function<Long, Category> categoryMapper =
                    ID -> {
                        CategoryDTO categoryDTO = categoryService.findById(ID);
                        return categoryDTO == null ? null : categoryDTO.convertToCategoryEntity(businessMapper());
                    };
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            String uploadDir = URLUtils.BUSINESS + "/" + id + URLUtils.CATEGORY + "/" + id2 + URLUtils.PRODUCT + "/photos/";
            productDTO.setImages(uploadDir + fileName);
            ProductDTO productDTO2 = productService.save(productDTO.convertToProductEntity(categoryMapper));
            businessDTO.getProducts().add(productDTO2);
            Business business = businessDTO.convertToBusinessEntity();
            businessDTO = businessService.save(business);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            return productDTO2;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT)
    public List<ProductDTO> getAllProducts(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null && categoryService.findById(id) != null)
            return productService.findAllByCategoryId(id2)
                    .stream().filter(it -> it.getQuantity() > 0).collect(Collectors.toList());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

}
