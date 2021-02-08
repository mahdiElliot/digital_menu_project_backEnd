package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.product.Product;
import com.example.project.model.purchase.RequestPurchaseDTO;
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

    private double tax;

    @NotNull
    private Integer table_number;

    private double service_tip;

    private String comment;

    @NotNull
    private Long business_id;

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private Long paymethod_id;

    Set<RequestPurchaseDTO> purchases;

    public Order convertToOrderEntity(Business business, PayMethod payMethod) {
        return new Order(id, tax, table_number, service_tip, comment, business, customer.convertToCustomerEntity(), payMethod);
    }

    @Override
    public String toString() {
        return "RequestOrderDTO{" +
                "id=" + id +
                ", tax=" + tax +
                ", table_number=" + table_number +
                ", comment='" + comment + '\'' +
                ", business_id=" + business_id +
                ", customer=" + customer +
                ", paymethod_id=" + paymethod_id +
                ", purchases=" + purchases +
                '}';
    }
}
