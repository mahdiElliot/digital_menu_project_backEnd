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

@Setter
@Getter
public class SpecificProductDTO {

    private Long id;

    private String name;

    private String comment;

    private Integer quantity;

    private Double price;

    private Set<OptionDTO> options;

    private Long product_id;

    private Set<OrderDTO> orders;

    public SpecificProductDTO(long id, String name, String comment, int quantity, double price, Set<OptionDTO> options, Long product_id, Set<OrderDTO> orders) {
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
