package com.example.project.model.menu;

import lombok.Getter;
import lombok.Setter;

public class MenuDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Boolean pickup;

    @Setter
    @Getter
    private Boolean delivery;

    @Setter
    @Getter
    private Boolean enabled;
}
