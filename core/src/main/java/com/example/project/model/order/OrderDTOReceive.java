package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.product.Product;
import com.example.project.model.specproduct.SpecificProductDTO;
import com.example.project.model.specproduct.SpecificProductDTOReceive;
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
public class OrderDTOReceive {
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
    Set<SpecificProductDTOReceive> specificProducts;

    public OrderDTOReceive() {
        super();
    }

    public Order convertToOrderEntity(@NotNull Function<Long, Business> getBusiness,
                                      @NotNull Function<Long, PayMethod> getPayMethod) {
        Business business = getBusiness.apply(business_id);
        Order order = new Order(
                id,
                tax,
                table_number,
                comment,
                business,
                customer.convertToCustomerEntity(),
                getPayMethod.apply(paymethod_id)
        );
        Set<Product> products = business.getProducts();
        Map<Long, Product> map = products.stream().collect(Collectors.toMap(Product::getId, e -> e));
        Function<Long, Product> productMapper = map::get;
        order.setSpecificProducts(specificProducts.stream()
                .map(it -> it.convertToSpecificProductEntity(productMapper)).collect(Collectors.toSet()));
        return order;
    }
}
