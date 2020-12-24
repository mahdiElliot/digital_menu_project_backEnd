package com.example.project.model.suboptions;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class SubOptionDTO {
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    private Long option_id;

    public SubOptionDTO(long id, int price, String name, String description, boolean enabled, String image, long option_id) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.image = image;
        this.option_id = option_id;
    }

    public SubOption convertToSubOptionEntity(@NotNull Function<Long, Option> getOption){
        return new SubOption(
                id,
                price,
                name,
                description,
                enabled,
                image,
                getOption.apply(option_id)
        );
    }
}
