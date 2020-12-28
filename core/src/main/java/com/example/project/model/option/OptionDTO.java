package com.example.project.model.option;

import com.example.project.model.extra.Extra;
import com.example.project.model.suboptions.SubOptionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class OptionDTO {

    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private Integer min;

    @NotNull
    private Integer max;

    @NotNull
    private Boolean enabled;

    private String image;

    private Set<SubOptionDTO> subOptions;

    private Long extra_id;

    public OptionDTO() {
        super();
    }

    public OptionDTO(long id, String name, int min, int max, boolean enabled, String image, Set<SubOptionDTO> subOptions, Long extra_id) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.enabled = enabled;
        this.image = image;
        this.subOptions = subOptions;
        this.extra_id = extra_id;
    }

    public Option convertToOptionEntity(@org.jetbrains.annotations.NotNull Function<Long, Extra> getExtra) {
        return new Option(
                id,
                name,
                min,
                max,
                enabled,
                image,
                getExtra.apply(extra_id)
        );
    }
}
