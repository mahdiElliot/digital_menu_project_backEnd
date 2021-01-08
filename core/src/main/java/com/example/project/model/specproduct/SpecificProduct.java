package com.example.project.model.specproduct;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.order.Order;
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
@Table(name = "specific_product")
public class SpecificProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sproduct_generator")
    @SequenceGenerator(name = "sproduct_generator", sequenceName = "sproduct_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String comment;

    @Column(nullable = false)
    private Integer quantity;

    private Double price;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "sproduct_option",
            joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "sproduct_id")}
    )
    private Set<Option> options = new HashSet<>();

    @ManyToMany(mappedBy = "specificProducts")
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public SpecificProduct(long id, String name, String comment, int quantity, double price, Product product) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.product = product;
    }

    public SpecificProductDTO convertToDTO() {
        Set<OptionDTO> optionDTOS = null;
        if (options != null)
            optionDTOS = options.stream().map(Option::convertToDTO).collect(Collectors.toSet());

        return new SpecificProductDTO(id, name, comment, quantity, price, product.getId(), optionDTOS);
    }
}
