package com.example.project.model.specproduct;

import com.example.project.model.option.Option;
import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

public class SpecificProductDTO {
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

    @Setter
    @Getter
    private Set<Option> options;

    @Setter
    @Getter
    private Long product_id;

    @Setter
    @Getter
    private Set<Order> orders;

    public SpecificProductDTO(long id, String comment, int quantity, double price, Set<Option> options, long product_id, Set<Order> orders) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.options = options;
        this.product_id = product_id;
        this.orders = orders;
    }

    public SpecificProduct convertToSpecificProductEntity(@NotNull Function<Long, Product> getProduct) {
        return new SpecificProduct(
                id,
                comment,
                quantity,
                price,
                options,
                getProduct.apply(product_id),
                orders
        );
    }
}
