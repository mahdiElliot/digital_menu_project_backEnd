package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Setter
@Getter
public class PayMethodDTO {

    private long id;

    private String data;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Boolean enabled;

    Long business_id;

    public PayMethodDTO() {
        super();
    }

    public PayMethodDTO(long id, String data, String name, boolean enabled, Long business_id) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.business_id = business_id;
    }

    public PayMethod convertToPayMethodEntity(@org.jetbrains.annotations.NotNull Function<Long, Business> getBusiness) {
        return new PayMethod(
                id,
                data,
                name,
                enabled,
                getBusiness.apply(business_id)
        );
    }
}
