package com.example.project.model.category;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class CategoryDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Integer rank;

    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    private Long business_id;

    public CategoryDTO(long id, String name, int rank, boolean enabled, String image) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
    }

    public Category convertToCategoryEntity(@NotNull Function<Long, Business> getBusiness) {
        return new Category(
                id,
                name,
                rank,
                enabled,
                image,
                getBusiness.apply(business_id)
        );
    }
}
