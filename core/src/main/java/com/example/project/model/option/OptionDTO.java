package com.example.project.model.option;

import com.example.project.model.extra.Extra;
import com.example.project.model.suboptions.SubOptionDTO;
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
public class OptionDTO {

    private long id;

    @NotEmpty
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

    public Option convertToOptionEntity(Extra extra) {
        return new Option(id, name, min, max, enabled, image, extra);
    }
}
