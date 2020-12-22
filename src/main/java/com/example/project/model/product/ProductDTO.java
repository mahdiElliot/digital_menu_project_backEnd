package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ProductDTO {

    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private String images;

    @Setter
    @Getter
    private boolean inventoried;

    @Setter
    @Getter
    private boolean featured;

    @Setter
    @Getter
    private boolean enabled;

    @Setter
    @Getter
    private boolean upselling;

    @Setter
    @Getter
    private Integer offer_price;

    @Setter
    @Getter
    private Integer rank;

    @Setter
    @Getter
    private Long menu_id;

    @Setter
    @Getter
    private Long category_id;

    public ProductDTO(Long id, Integer price, Integer quantity, String name, String description, String images, boolean inventoried, boolean featured, boolean enabled, boolean upselling, Integer offer_price, Integer rank, Long menu_id, Long category_id) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.images = images;
        this.inventoried = inventoried;
        this.featured = featured;
        this.enabled = enabled;
        this.upselling = upselling;
        this.offer_price = offer_price;
        this.rank = rank;
        this.menu_id = menu_id;
        this.category_id = category_id;
    }

    public Product convertToProductEntity(@NotNull Function<Long, Menu> getMenu,@NotNull Function<Long, Category> getCategory){
        return new Product(
                id,
                price,
                quantity,
                name,
                description,
                images,
                inventoried,
                featured,
                enabled,
                upselling,
                offer_price,
                rank,
                getMenu.apply(menu_id),
                getCategory.apply(category_id)
        );
    }
}
