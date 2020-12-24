package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.specproduct.SpecificProduct;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean inventoried;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @OneToMany(mappedBy = "product")
    @Setter
    @Getter
    private Set<SpecificProduct> specificProducts;

    @NotNull
    @ManyToMany(mappedBy = "products")
    @Setter
    @Getter
    private Set<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Setter
    @Getter
    private Category category;

    @ManyToMany(mappedBy = "products")
    @Setter
    @Getter
    private Set<Extra> extras;

    public Product() {
    }

    public Product(long id, int price, int quantity, String name, String description, String images,
                   boolean inventoried, boolean enabled, Category category) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.images = images;
        this.inventoried = inventoried;
        this.enabled = enabled;
        this.category = category;
    }

    public ProductDTO convertToDTO() {
        Set<ExtraDTO> extraDTOS = null;
        if (extras != null)
            extraDTOS = extras.stream().map(Extra::convertToDTO).collect(Collectors.toSet());

        long categoryId = 0;
        if (category != null)
            categoryId = category.getId();
        return new ProductDTO(
                id,
                price,
                quantity,
                name,
                description,
                images,
                inventoried,
                enabled,
                categoryId,
                extraDTOS
        );
    }
}
