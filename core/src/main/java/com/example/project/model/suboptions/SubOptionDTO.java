package com.example.project.model.suboptions;

import com.example.project.model.option.Option;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Getter
@Setter
public class SubOptionDTO {
    private long id;

    @NotNull
    private Double price;

    @NotNull
    @NotEmpty
    private String name;

    private String description;

    @NotNull
    private Boolean enabled;

    private String image;

    private Long option_id;

    public SubOptionDTO() {
        super();
    }

    public SubOptionDTO(long id, double price, String name, String description, boolean enabled, String image, @Nullable Long option_id) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.image = image;
        this.option_id = option_id;
    }

    public SubOption convertToSubOptionEntity(@NotNull Function<Long, Option> getOption) {
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
