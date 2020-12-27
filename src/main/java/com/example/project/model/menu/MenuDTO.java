package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class MenuDTO {
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Boolean enabled;

    private Long business_id;

    private Set<ProductDTO> products;

    public MenuDTO(long id, String name, boolean enabled, Long business_id, Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
        this.products = products;
    }

    public Menu convertToMenuEntity(@org.jetbrains.annotations.NotNull Function<Long, Business> getBusiness) {
        return new Menu(
                id,
                name,
                enabled,
                getBusiness.apply(business_id)
        );
    }

}
