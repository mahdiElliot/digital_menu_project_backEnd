package com.example.project.model.category;

import com.example.project.model.business.Business;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class CategoryDTO {
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Integer rank;

    @NotNull
    private Boolean enabled;

    private String image;

    private Long business_id;

    private Set<ProductDTO> products;

    public CategoryDTO() {
        super();
    }

    public CategoryDTO(long id, String name, int rank, boolean enabled, String image, Long business_id, Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
        this.business_id = business_id;
        this.products = products;
    }

    public Category convertToCategoryEntity(Business business) {
        return new Category(id, name, rank, enabled, image, business);
    }
}
