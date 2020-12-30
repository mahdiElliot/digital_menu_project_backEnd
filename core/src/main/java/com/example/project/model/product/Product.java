package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.specproduct.SpecificProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    private Integer price;

    private Integer quantity;

    private String name;

    private String description;

    private String images;

    @Column(nullable = false)
    private Boolean inventoried;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "product")
    private Set<SpecificProduct> specificProducts = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Menu> menus = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Extra> extras = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Business> businesses = new HashSet<>();

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

        Long categoryId = null;
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
