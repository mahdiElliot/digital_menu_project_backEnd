package com.example.project.model.order;

import com.example.project.model.specproduct.PurchaseDTO;
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

    private Date created_at;

    @NotEmpty
    Set<PurchaseDTO> specificProducts;

    public OrderDTO(long id, double tax, int table_number, String comment, Long business_id, Long customer_id, Long paymethod_id, Date created_at) {
        this.id = id;
        this.tax = tax;
        this.table_number = table_number;
        this.comment = comment;
        this.business_id = business_id;
        this.customer_id = customer_id;
        this.paymethod_id = paymethod_id;
        this.created_at = created_at;
    }
}
