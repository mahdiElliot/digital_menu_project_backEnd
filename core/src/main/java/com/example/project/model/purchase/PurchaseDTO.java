package com.example.project.model.purchase;

import com.example.project.model.option.OptionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private long id;

    private String comment;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Long product_id;

    private Long order_id;

    private Set<OptionDTO> options;
}
