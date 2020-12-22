package com.example.project.model.option;

import com.example.project.model.suboptions.SubOption;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class OptionDTO {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer min;

    @Setter
    @Getter
    private Integer max;

    @Setter
    @Getter
    private boolean enabled;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    private Set<SubOption> subOptions;

    public OptionDTO(Long id, String name, Integer min, Integer max, boolean enabled, String image, Set<SubOption> subOptions) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.enabled = enabled;
        this.image = image;
        this.subOptions = subOptions;
    }

    public Option convertToOptionEntity(){
        return new Option(
                id,
                name,
                min,
                max,
                enabled,
                image,
                subOptions
        );
    }
}
