package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long id;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @NotEmpty
    private String name;

    private String description;

    private String images;

    @NotNull
    private Boolean inventoried;

    @NotNull
    private Boolean enabled;

    private Long category_id;

    private Long business_id;

    private Set<ExtraDTO> extras;

    public Product convertToProductEntity(Category category) {
        Product product =
                new Product(id, price, quantity, name, description, images, inventoried, enabled, category, category.getBusiness());

        if (extras != null && !extras.isEmpty()) {
            product.setExtras(extras.stream()
                    .map(it -> it.convertToExtraEntity(category.getBusiness())).collect(Collectors.toSet()));
        }
        return product;
    }
}
