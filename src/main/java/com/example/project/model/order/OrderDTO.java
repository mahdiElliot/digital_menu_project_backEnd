package com.example.project.model.order;

import com.example.project.model.business.Business;
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

    public OrderDTO(long id, double tax, int table_number, long business_id) {
        this.id = id;
        this.tax = tax;
        this.table_number = table_number;
        this.business_id = business_id;
    }

    public COrder convertToOrderEntity(@NotNull Function<Long, Business> getBusiness) {
        return new COrder(
                id,
                tax,
                table_number,
                getBusiness.apply(business_id)
        );
    }
}
