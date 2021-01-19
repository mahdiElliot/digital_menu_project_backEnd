package com.example.project.model.extra;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.product.Product;
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
@Table(name = "extra")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extra_generator")
    @SequenceGenerator(name = "extra_generator", sequenceName = "extra_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "extra", cascade = CascadeType.ALL)
    private Set<Option> options = new HashSet<>();

    @ManyToMany(mappedBy = "extras", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

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
        Long businessId = null;
        if (business != null)
            businessId = business.getId();
        return new ExtraDTO(id, name, description, enabled, businessId, optionDTOS);
    }

    @Override
    public String toString() {
        return "Extra{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                ", business=" + business.getId() +
                '}';
    }
}
