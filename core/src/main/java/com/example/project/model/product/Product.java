package com.example.project.model.product;

import com.example.project.model.business.Business;
import com.example.project.model.category.Category;
import com.example.project.model.extra.Extra;
import com.example.project.model.extra.ExtraDTO;
import com.example.project.model.menu.Menu;
import com.example.project.model.purchase.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    private String name;

    private String description;

    private String images;

    @Column(nullable = false)
    private Boolean inventoried;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_info",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "extra_id")}
    )
    private Set<Extra> extras = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Purchase> products = new HashSet<>();

    public Product(long id, double price, int quantity, String name, String description, String images,
                   boolean inventoried, boolean enabled, Category category, Business business) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.images = images;
        this.inventoried = inventoried;
        this.enabled = enabled;
        this.category = category;
        this.business = business;
    }

    public ProductDTO convertToDTO() {
        Set<ExtraDTO> extraDTOS = null;
        if (extras != null)
            extraDTOS = extras.stream().map(Extra::convertToDTO).collect(Collectors.toSet());

        Long categoryId = null;
        if (category != null) categoryId = category.getId();

        Long businessId = null;
        if (business != null) businessId = business.getId();

        ProductDTO productDTO =
                new ProductDTO(id, price, quantity, name, description, images, inventoried, enabled, categoryId, businessId, extraDTOS);
        return productDTO;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", images='" + images + '\'' +
                ", inventoried=" + inventoried +
                ", enabled=" + enabled +
                ", business=" + business +
                '}';
    }
}
