package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.specproduct.SpecificProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Collection;
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

    String comment;

    private Long business_id;

    @NotNull
    private Long customer_id;

    @NotNull
    private Long paymethod_id;

    @NotNull
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

    public Order convertToOrderEntity(@NotNull Function<Long, Business> getBusiness,
                                      @NotNull Function<Long, Customer> getCustomer,
                                      @NotNull Function<Long, PayMethod> getPayMethod) {
        Order order = new Order(
                id,
                tax,
                table_number,
                comment,
                getBusiness.apply(business_id),
                getCustomer.apply(customer_id),
                getPayMethod.apply(paymethod_id)
        );
        if (specificProducts != null)
            order.setSpecificProducts(specificProducts.stream()
                    .map(SpecificProductDTO::convertToSpecificProductEntity).collect(Collectors.toSet()));
        return order;
    }
}
