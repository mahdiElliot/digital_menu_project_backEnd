package com.example.project.model.category;

import com.example.project.model.business.Business;
import com.example.project.model.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer rank;

    @NotNull
    private Boolean enabled;

    private String image;

    private Long business_id;

    private Set<ProductDTO> products;

    public Category convertToCategoryEntity(Business business) {
        return new Category(id, name, rank, enabled, image, business);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", enabled=" + enabled +
                ", image='" + image + '\'' +
                ", business_id=" + business_id +
                '}';
    }
}
