package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    Set<Business> businesses;

    public PayMethodDTO(long id, String data, String name, boolean enabled, Set<Business> businesses) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.businesses = businesses;
    }

    public PayMethod convertToPayMethodEntity() {
        return new PayMethod(
                id,
                data,
                name,
                enabled,
                businesses
        );
    }
}
