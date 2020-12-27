package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
    @SequenceGenerator(name = "menu_generator", sequenceName = "menu_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "menu_product",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products;


    public Menu() {
    }

    public Menu(long id, String name, boolean enabled, Business business) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business = business;
    }

    public MenuDTO convertToDTO() {
        Set<ProductDTO> productDTOS = null;
        if (products != null)
            productDTOS = products.stream().map(Product::convertToDTO).collect(Collectors.toSet());

        Long businessId = null;
        if (business != null)
            businessId = business.getId();
        return new MenuDTO(
                id,
                name,
                enabled,
                businessId,
                productDTOS
        );
    }
}
