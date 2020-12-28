package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.paymethod.PayMethod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

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
        return new Order(
                id,
                tax,
                table_number,
                comment,
                getBusiness.apply(business_id),
                getCustomer.apply(customer_id),
                getPayMethod.apply(paymethod_id)
        );
    }
}
