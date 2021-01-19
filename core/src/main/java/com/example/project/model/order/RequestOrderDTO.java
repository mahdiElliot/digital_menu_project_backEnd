package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.product.Product;
import com.example.project.model.specproduct.RequestPurchaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class RequestOrderDTO {
    private long id;

    @NotNull
    private Double tax;

    @NotNull
    private Integer table_number;

    private String comment;

    @NotNull
    private Long business_id;

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private Long paymethod_id;

    @NotEmpty
    Set<RequestPurchaseDTO> purchases;

    public Order convertToOrderEntity(Business business, PayMethod payMethod) {
        Order order = new Order(id, tax, table_number, comment, business, customer.convertToCustomerEntity(), payMethod);
        Set<Product> products = business.getProducts();
        Map<Long, Product> map = products.stream().collect(Collectors.toMap(Product::getId, e -> e));
        Map<Long, Order> orderMap = business.getOrders().stream().collect(Collectors.toMap(Order::getId, e -> e));

        order.setPurchases(purchases.stream()
                .map(it -> it.convertToPurchaseEntity(map.get(it.getId()), orderMap.get(it.getId())))
                .collect(Collectors.toSet()));
        return order;
    }
}
