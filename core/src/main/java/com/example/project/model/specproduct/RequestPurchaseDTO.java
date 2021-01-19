package com.example.project.model.specproduct;

import com.example.project.model.extra.Extra;
import com.example.project.model.option.Option;
import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class RequestPurchaseDTO {
    private long id;

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

    private Long order_id;

    private Set<Long> options;

    public Purchase convertToPurchaseEntity(Product product, Order order) {
        Purchase purchase = new Purchase(id, name, comment, quantity, price, product, order);
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
            purchase.setOptions(optionSet);
        }
        return purchase;
    }
}
