package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.product.Product;
import com.example.project.model.specproduct.SpecificProductDTO;
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
public class OrderDTO {
    private long id;

    @NotNull
    private Double tax;

    @NotNull
    private Integer table_number;

    private String comment;

    @NotNull
    private Long business_id;

    @NotNull
    private Long customer_id;

    @NotNull
    private Long paymethod_id;

    @NotNull
    @NotEmpty
    Set<SpecificProductDTO> specificProducts;

    public OrderDTO() {
        super();
    }

    public OrderDTO(long id, double tax, int table_number, String comment, Long business_id, Long customer_id, Long paymethod_id) {
        this.id = id;
        this.tax = tax;
        this.table_number = table_number;
        this.comment = comment;
        this.business_id = business_id;
        this.customer_id = customer_id;
        this.paymethod_id = paymethod_id;
    }

    public Order convertToOrderEntity(@org.jetbrains.annotations.NotNull Function<Long, Business> getBusiness,
                                      @org.jetbrains.annotations.NotNull Function<Long, Customer> getCustomer,
                                      @org.jetbrains.annotations.NotNull Function<Long, PayMethod> getPayMethod) {
        Business business = getBusiness.apply(business_id);
        Order order = new Order(
                id,
                tax,
                table_number,
                comment,
                business,
                getCustomer.apply(customer_id),
                getPayMethod.apply(paymethod_id)
        );
        if (specificProducts != null && !specificProducts.isEmpty()) {
            Set<Product> products = business.getProducts();
            Map<Long, Product> map =
                    products == null ? null : products.stream().collect(Collectors.toMap(Product::getId, e -> e));
            Function<Long, Product> productMapper = id -> map == null ? null : map.get(id);
            order.setSpecificProducts(specificProducts.stream()
                    .map(it -> it.convertToSpecificProductEntity(productMapper.apply(it.getId()))).collect(Collectors.toSet()));
        }
        return order;
    }
}
