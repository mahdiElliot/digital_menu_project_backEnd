package com.example.project.controller;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.product.ProductDTO;
import com.example.project.service.business.IBusinessService;
import com.example.project.service.category.ICategoryService;
import com.example.project.service.product.IProductService;
import com.example.project.utils.ErrorUtils;
import com.example.project.utils.FileUploadUtil;
import com.example.project.utils.URLUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = URLUtils.BASE)
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
            @RequestParam(name = "extra", required = false) String extras,
            @RequestParam(name = "photo", required = false) MultipartFile multipartFile,
            BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);

        Business business = businessMapper().apply(id);
        CategoryDTO categoryDTO = categoryService.findById(id2);
        if (business != null && categoryDTO != null) {
            productDTO.setCategory_id(id2);
            productDTO.setBusiness_id(id);
            productDTO.setExtras(convertExtraToJson(extras));

            if (multipartFile != null) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                String uploadDir = URLUtils.BUSINESS + "/" + id + URLUtils.CATEGORY + "/" + id2 + URLUtils.PRODUCT + "/photos/";
                productDTO.setImages(uploadDir + fileName);
            }
            return productService.save(productDTO.convertToProductEntity(categoryDTO.convertToCategoryEntity(business)));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT)
    public List<ProductDTO> getAllProducts(@PathVariable("id") Long id, @PathVariable("id2") Long id2) {
        if (businessService.findById(id) != null && categoryService.findById(id2) != null)
            return productService.findAllByCategoryId(id2)
                    .stream().filter(it -> it.getQuantity() > 0).collect(Collectors.toList());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

    @GetMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT + "/{id3}")
    public ProductDTO getProduct(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3) {
        if (businessService.findById(id) != null && categoryService.findById(id2) != null) {
            ProductDTO productDTO = productService.findById(id3);
            if (productDTO == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorUtils.NOT_FOUND);
            return productDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

//    @PutMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT + "/{id3}")
//    public ProductDTO update(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3, @RequestParam(name = "photo", required = false) MultipartFile multipartFile, @Valid ProductDTO productDTO,
//                             @RequestParam(name = "extra", required = false) String extras, BindingResult bindingResult) throws JsonProcessingException {
//        if (bindingResult.hasErrors())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorUtils.NULL_EMPTY);
//
//        Business business = businessMapper().apply(id);
//        CategoryDTO categoryDTO = categoryService.findById(id2);
//        if (categoryDTO != null) {
//            productDTO.setId(id3);
//            productDTO.setCategory_id(id2);
//            productDTO.setExtras(convertExtraToJson(extras));
//            if (multipartFile != null) {
//                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//                String uploadDir = URLUtils.BUSINESS + "/" + id + URLUtils.CATEGORY + "/" + id2 + URLUtils.PRODUCT + "/photos/";
//                productDTO.setImages(uploadDir + fileName);
//            }
//            return productService.save(productDTO.convertToProductEntity(categoryDTO.convertToCategoryEntity(business)));
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category " + ErrorUtils.NOT_FOUND);
//    }

    @DeleteMapping(path = URLUtils.BUSINESS + "/{id}" + URLUtils.CATEGORY + "/{id2}" + URLUtils.PRODUCT + "/{id3}")
    public ProductDTO delete(@PathVariable("id") Long id, @PathVariable("id2") Long id2, @PathVariable("id3") Long id3) {
        if (businessService.findById(id) != null && categoryService.findById(id2) != null) {
            return productService.delete(id3);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "business or category " + ErrorUtils.NOT_FOUND);
    }

    private Set<ExtraDTO> convertExtraToJson(String extras) throws JsonProcessingException {
        if (extras != null && !extras.isBlank()) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(extras, new TypeReference<>() {
            });
        }
        return Collections.emptySet();
    }
}
