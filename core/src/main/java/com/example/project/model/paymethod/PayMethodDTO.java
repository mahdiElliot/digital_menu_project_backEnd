package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PayMethodDTO {

    private long id;

    private String data;

    @NotEmpty
    private String name;

    @NotNull
    private Boolean enabled;

    Long business_id;

    public PayMethod convertToPayMethodEntity(Business business) {
        return new PayMethod(id, data, name, enabled, business);
    }
}
