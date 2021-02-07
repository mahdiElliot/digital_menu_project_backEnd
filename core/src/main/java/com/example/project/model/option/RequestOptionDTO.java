package com.example.project.model.option;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOptionDTO {
    private long id;

    private Set<Long> subOptions;
}
