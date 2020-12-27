package com.example.project.model.option;

import com.example.project.model.extra.Extra;
import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

@Setter
@Getter
public class OptionDTO {

    private Long id;

    private String name;

    private Integer min;

    private Integer max;

    private Boolean enabled;

    private String image;

    private Set<SubOptionDTO> subOptions;

    private Long extra_id;

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

    public Option convertToOptionEntity(@NotNull Function<Long, Extra> getExtra) {
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
