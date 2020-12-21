package com.example.project.model.extra;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ExtraDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    private Long business_id;

    public ExtraDTO(long id, String name, String description, boolean enabled, long business_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.business_id = business_id;
    }

    public Extra convertToExtraEntity(@NotNull Function<Long, Business> getBusiness) {
        return new Extra(
                id,
                name,
                description,
                enabled,
                getBusiness.apply(business_id)
        );
    }
}
