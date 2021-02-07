package com.example.project.model.order;

import com.example.project.model.business.BusinessDTO;
import com.example.project.model.customer.CustomerDTO;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.paymethod.PayMethodDTO;
import com.example.project.model.purchase.PurchaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class OrderDTO {
    private long id;

    private Double tax;

    private Integer table_number;

    private String comment;

    private Long business_id;

    private BusinessDTO business;

    private Double service_fee;

    private double service_tip;

    private CustomerDTO customer;

    private PayMethodDTO paymethod;

    private Date created_at;

    @NotEmpty
    Set<PurchaseDTO> purchases;

    public OrderDTO(long id, double tax, int table_number, double service_tip, String comment, BusinessDTO business, CustomerDTO customer, PayMethodDTO paymethod, Date created_at) {
        this.id = id;
        this.tax = tax;
        this.table_number = table_number;
        this.comment = comment;
        this.business = business;
        if (business != null) {
            service_fee = business.getService_fee();
            business_id = business.getId();
        }
        this.customer = customer;
        this.paymethod = paymethod;
        this.created_at = created_at;
        this.service_tip = service_tip;
    }
}
