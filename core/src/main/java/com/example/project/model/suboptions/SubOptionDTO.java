package com.example.project.model.suboptions;

import com.example.project.model.option.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOptionDTO {
    private long id;

    @NotNull
    private Double price;

    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Boolean enabled;

    private String image;

    private Long option_id;

    public SubOption convertToSubOptionEntity(Option option) {
        return new SubOption(id, price, name, description, enabled, image, option);
    }
}
