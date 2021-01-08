package com.example.project.model.extra;

import com.example.project.model.business.Business;
import com.example.project.model.option.OptionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDTO {
    private long id;

    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Boolean enabled;

    private Long business_id;

    private Set<OptionDTO> options;

    public Extra convertToExtraEntity(Business business) {
        return new Extra(id, name, description, enabled, business);
    }
}
