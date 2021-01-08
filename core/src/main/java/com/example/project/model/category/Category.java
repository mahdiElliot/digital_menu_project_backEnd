package com.example.project.model.category;

import com.example.project.model.business.Business;
import com.example.project.model.product.Product;
import com.example.project.model.product.ProductDTO;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer rank;

    @Column(nullable = false)
    private Boolean enabled;

    private String image;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category(long id, String name, int rank, boolean enabled, String image, Business business) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
        this.business = business;
    }

    public CategoryDTO convertToDTO() {
        Long businessId = null;
        if (business != null)
            businessId = business.getId();

        Set<ProductDTO> productDTOS = null;
        if (products != null)
            productDTOS = products.stream().map(Product::convertToDTO).collect(Collectors.toSet());

        return new CategoryDTO(id, name, rank, enabled, image, businessId, productDTOS);
    }
}
