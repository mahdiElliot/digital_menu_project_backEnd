package com.example.project.model.menu;

import com.example.project.model.business.Business;
import com.example.project.model.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

public class MenuDTO {
    @Getter
    @Setter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Boolean enabled;

    @Getter
    @Setter
    private Long business_id;

    @Setter
    @Getter
    private Set<ProductDTO> products;

    public MenuDTO(long id, String name, boolean enabled, Long business_id, Set<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
        this.products = products;
    }

    public Menu convertToMenuEntity(@NotNull Function<Long, Business> getBusiness) {
        return new Menu(
                id,
                name,
                enabled,
                getBusiness.apply(business_id)
        );
    }

}
