package com.example.project.model.specproduct;

import com.example.project.model.option.Option;
import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specific_product")
public class SpecificProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String comment;

    @Setter
    @Getter
    private Integer quantity;

    @Setter
    @Getter
    private Double price;

    @ManyToMany(mappedBy = "specificProducts")
    @Setter
    @Getter
    private Set<Option> options;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Setter
    @Getter
    private Product product;

    @ManyToMany(mappedBy = "specificProducts")
    @Setter
    @Getter
    private Set<Order> orders;

    public SpecificProduct() {
    }

    public SpecificProduct(long id, String comment, int quantity, double price,
                           Set<Option> options, Product product, Set<Order> orders) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.options = options;
        this.product = product;
        this.orders = orders;
    }
}
