package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.product.Product;
import com.example.project.model.specproduct.RequestSpecificProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
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

    @NotNull
    @NotEmpty
    Set<RequestSpecificProductDTO> specificProducts;

    public RequestOrderDTO() {
        super();
    }

    public Order convertToOrderEntity(Business business, PayMethod payMethod) {
        Order order = new Order(
                id,
                tax,
                table_number,
                comment,
                business,
                customer.convertToCustomerEntity(),
                payMethod
        );
        Set<Product> products = business.getProducts();
        Map<Long, Product> map = products.stream().collect(Collectors.toMap(Product::getId, e -> e));
        order.setSpecificProducts(specificProducts.stream()
                .map(it -> it.convertToSpecificProductEntity(map.get(it.getId()))).collect(Collectors.toSet()));
        return order;
    }
}