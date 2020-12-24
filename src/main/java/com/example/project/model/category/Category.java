package com.example.project.model.category;

import com.example.project.model.business.Business;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer rank;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    private String image;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    @OneToMany(mappedBy = "category")
    @Setter
    @Getter
    private Set<Product> products;

    public Category() {
    }

    public Category(long id, String name, int rank, boolean enabled, String image, Business business) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
        this.business = business;
    }

    public CategoryDTO convertToDTO() {
        long businessId = 0;
        if (business != null)
            businessId = business.getId();

        Set<ProductDTO> productDTOS = null;
        if (products != null)
            productDTOS = products.stream().map(Product::convertToDTO).collect(Collectors.toSet());

        return new CategoryDTO(
                id,
                name,
                rank,
                enabled,
                image,
                businessId,
                productDTOS
        );
    }
}
