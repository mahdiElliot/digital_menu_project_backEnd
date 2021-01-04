package com.example.project.model.specproduct;

import com.example.project.model.extra.Extra;
import com.example.project.model.option.Option;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
public class SpecificProductReceive {
    private long id;

    @NotNull
    @NotEmpty
    private String name;

    private String comment;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Long product_id;

    private Set<Long> options;

    public SpecificProductReceive() {
        super();
    }

    public SpecificProduct convertToSpecificProductEntity(@NotNull Function<Long, Product> getProduct) {
        Product product = getProduct.apply(product_id);
        price = product.getPrice();
        SpecificProduct specificProduct = new SpecificProduct(id, name, comment, quantity, price, product);
        if (options != null && !options.isEmpty()) {
            Set<Extra> extras = product.getExtras();
            Set<Option> optionSet = new HashSet<>();
            for (Extra extra : extras) {
                if (extra.getOptions() == null) continue;
                Map<Long, Option> map = extra.getOptions().stream().collect(Collectors.toMap(Option::getId, e -> e));
                for (Long id : options) {
                    Option option = map.get(id);
                    if (option != null) optionSet.add(option);
                }
            }
            specificProduct.setOptions(optionSet);
        }
        return specificProduct;
    }
}
