package com.example.project.service.product;

import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import com.example.project.service.IService;

import java.util.List;

public interface IProductService extends IService<Product, ProductDTO, Long> {

    List<ProductDTO> findAllByCategoryId(Long id);
}
