package com.example.project.model.specproduct;

import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.order.Order;
import com.example.project.model.order.OrderDTO;
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

    @Setter
    @Getter
    private Set<OptionDTO> options;

    @Setter
    @Getter
    private Long product_id;

    @Setter
    @Getter
    private Set<OrderDTO> orders;

    public SpecificProductDTO(long id, String name, String comment, int quantity, double price, Set<OptionDTO> options, long product_id, Set<OrderDTO> orders) {
        this.id = id;
        this.name = name;
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
                name,
                comment,
                quantity,
                price,
                getProduct.apply(product_id)
        );
    }
}
