package com.example.project.model.menu;

import com.example.project.model.DTO;
import lombok.Getter;
import lombok.Setter;

public class MenuDTO extends DTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Boolean enabled;

    @Getter
    @Setter
    private Long businessId;
}
