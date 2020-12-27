package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.category.CategoryDTO;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.menu.MenuDTO;
import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.specproduct.SpecificProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class ProductDTO {

    private Long id;

    private Integer price;

    private Integer quantity;

    private String name;

    private String description;

    private String images;

    private Boolean inventoried;

    private Boolean enabled;

    private Long category_id;

    private Set<ExtraDTO> extras;

    public ProductDTO(long id, int price, int quantity, String name, String description, String images,
                      boolean inventoried, boolean enabled, Long category_id, Set<ExtraDTO> extras) {
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
        return new Product(
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
    }
}
