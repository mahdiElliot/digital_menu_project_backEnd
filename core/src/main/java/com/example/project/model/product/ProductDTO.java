package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.extra.ExtraDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
public class ProductDTO {

    private long id;

    @NotNull
    private Integer price;

    @NotNull
    private Integer quantity;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    private String images;

    @NotNull
    private Boolean inventoried;

    @NotNull
    private Boolean enabled;

    private Long category_id;

    private Set<ExtraDTO> extras;

    @JsonIgnore
    @Nullable
    private Set<Business> businesses;

    public ProductDTO() {
        super();
    }

    public ProductDTO(long id, int price, int quantity, String name, String description, String images,
                      boolean inventoried, boolean enabled, Long category_id, @Nullable Set<ExtraDTO> extras) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.images = images;
        this.inventoried = inventoried;
        this.enabled = enabled;
        this.category_id = category_id;
        this.extras = extras;
    }

    public Product convertToProductEntity(@NotNull Function<Long, Category> getCategory) {
        Product product = new Product(
                id,
                price,
                quantity,
                name,
                description,
                images,
                inventoried,
                enabled,
                getCategory.apply(category_id)
        );
        if (extras != null) {
            Function<Long, Business> businessMapper =
                    id -> businesses == null ? null :
                            businesses.stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
            product.setExtras(extras.stream()
                    .map(it -> it.convertToExtraEntity(businessMapper)).collect(Collectors.toSet()));
        }
        return product;
    }
}
