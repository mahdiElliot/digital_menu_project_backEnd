package com.example.project.model.extra;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class ExtraDTO {
    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    private Long business_id;

    private Set<OptionDTO> options;

    public ExtraDTO(long id, String name, String description, boolean enabled, Long business_id, Set<OptionDTO> options) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.business_id = business_id;
        this.options = options;
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
