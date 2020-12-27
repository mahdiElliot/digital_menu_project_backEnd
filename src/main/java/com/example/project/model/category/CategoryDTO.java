package com.example.project.model.category;

import com.example.project.model.business.Business;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class CategoryDTO {
    private Long id;

    private String name;

    private Integer rank;

    private Boolean enabled;

    private String image;

    private Long business_id;

    private Set<ProductDTO> products;

    public CategoryDTO(long id, String name, int rank, boolean enabled, String image, Long business_id, Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
        this.business_id = business_id;
        this.products = products;
    }

    public Category convertToCategoryEntity(@NotNull Function<Long, Business> getBusiness) {
        return new Category(
                id,
                name,
                rank,
                enabled,
                image,
                getBusiness.apply(business_id)
        );
    }
}
