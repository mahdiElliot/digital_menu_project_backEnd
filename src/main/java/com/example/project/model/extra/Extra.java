package com.example.project.model.extra;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "extra")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false)
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    @OneToMany(mappedBy = "extra")
    @Setter
    @Getter
    private Set<Option> options;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_info",
            joinColumns = {@JoinColumn(name = "extra_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    @Setter
    @Getter
    private Set<Product> products;

    public Extra() {
    }

    public Extra(long id, String name, String description, boolean enabled, Business business) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.business = business;
    }

    public ExtraDTO convertToDTO() {
        Set<OptionDTO> optionDTOS = null;
        if (options != null)
            optionDTOS = options.stream().map(Option::convertToDTO).collect(Collectors.toSet());
        long businessId = 0;
        if (business != null)
            businessId = business.getId();
        return new ExtraDTO(
                id,
                name,
                description,
                enabled,
                businessId,
                optionDTOS
        );
    }
}
