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
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_generator")
    @SequenceGenerator(name = "purchase_generator", sequenceName = "purchase_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String comment;

    @Column(nullable = false)
    private Integer quantity;

    private Double price;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "purchase_option",
            joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "purchase_id")}
    )
    private Set<Option> options = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Purchase(long id, String name, String comment, int quantity, double price, Product product, Order order) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.product = product;
        this.order = order;
    }

    public PurchaseDTO convertToDTO() {
        Set<OptionDTO> optionDTOS = null;
        if (options != null)
            optionDTOS = options.stream().map(Option::convertToDTO).collect(Collectors.toSet());

        return new PurchaseDTO(id, name, comment, quantity, price, product.getId(), order.getId(), optionDTOS);
    }
}
