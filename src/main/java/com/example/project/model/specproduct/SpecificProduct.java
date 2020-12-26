package com.example.project.model.specproduct;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

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
    private String name;

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
        Set<OrderDTO> orderDTOS = null;
        if (orders != null)
            orderDTOS = orders.stream().map(Order::convertToDTO).collect(Collectors.toSet());
        Long productId = null;
        if (product != null)
            productId = product.getId();
        return new SpecificProductDTO(
                id,
                name,
                comment,
                quantity,
                price,
                optionDTOS,
                productId,
                orderDTOS
        );
    }
}
