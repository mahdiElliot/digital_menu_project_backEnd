package com.example.project.model.specproduct;

import com.example.project.model.extra.Extra;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.order.OrderDTO;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
public class SpecificProductDTO {

    private long id;

    @NotNull
    @NotEmpty
    private String name;

    private String comment;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    private Set<OptionDTO> options;

    public SpecificProductDTO() {
        super();
    }

    public SpecificProductDTO(long id, String name, String comment, int quantity, double price, @Nullable Set<OptionDTO> options) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.options = options;
    }

    public SpecificProduct convertToSpecificProductEntity() {
        SpecificProduct specificProduct = new SpecificProduct(id, name, comment, quantity, price);
        if (options != null) {
            Function<Long, Extra> extraMapper = id -> null;
            specificProduct.setOptions(options.stream()
                    .map(it -> it.convertToOptionEntity(extraMapper)).collect(Collectors.toSet()));
        }
        return specificProduct;
    }
}
