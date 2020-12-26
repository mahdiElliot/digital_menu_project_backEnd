package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import com.example.project.model.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "pay_method")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String data;

    @Column(nullable = false)
    @Setter
    @Getter
    private String name;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    @OneToMany(mappedBy = "payMethod")
    @Setter
    @Getter
    private Set<Order> orders;

    public PayMethod() {
    }

    public PayMethod(long id, String data, String name, boolean enabled, Business business) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.business = business;
    }

    public PayMethodDTO convertToDTO() {
        Long businessId = null;
        if (business != null)
            businessId = business.getId();
        return new PayMethodDTO(
                id,
                data,
                name,
                enabled,
                businessId
        );
    }
}
