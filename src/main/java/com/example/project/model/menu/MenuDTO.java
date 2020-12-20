package com.example.project.model.menu;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

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

    public MenuDTO(Long id, String name, boolean enabled, long business_id) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
    }

    public Menu convertToMenuEntity(Function<Long, Business> getBusiness) {
        return new Menu(
                id,
                name,
                enabled,
                getBusiness.apply(business_id)
        );
    }
}
