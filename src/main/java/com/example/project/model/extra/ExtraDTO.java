package com.example.project.model.extra;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class ExtraDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    @NotNull
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

    public Extra convertToExtraEntity(@org.jetbrains.annotations.NotNull Function<Long, Business> getBusiness) {
        return new Extra(
                id,
                name,
                description,
                enabled,
                getBusiness.apply(business_id)
        );
    }
}
