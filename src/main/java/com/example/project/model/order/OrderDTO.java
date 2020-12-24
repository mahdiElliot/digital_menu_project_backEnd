package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class OrderDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Double tax;

    @Setter
    @Getter
    private Integer table_number;

    @Getter
    @Setter
    private Long business_id;

    @Setter
    @Getter
    private Long customer_id;

    public OrderDTO(long id, double tax, int table_number, long business_id, long customer_id) {
        this.id = id;
        this.tax = tax;
        this.table_number = table_number;
        this.business_id = business_id;
        this.customer_id = customer_id;
    }

    public Order convertToOrderEntity(@NotNull Function<Long, Business> getBusiness, @NotNull Function<Long, Customer> getCustomer) {
        return new Order(
                id,
                tax,
                table_number,
                getBusiness.apply(business_id),
                getCustomer.apply(customer_id)
        );
    }
}
