package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import com.example.project.model.business.BusinessDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Function;

public class PayMethodDTO {
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String data;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    Long business_id;

    public PayMethodDTO(long id, String data, String name, boolean enabled, Long business_id) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
    }

    public PayMethod convertToPayMethodEntity(@NotNull Function<Long, Business> getBusiness) {
        return new PayMethod(
                id,
                data,
                name,
                enabled,
                getBusiness.apply(business_id)
        );
    }
}
