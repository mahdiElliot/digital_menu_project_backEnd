package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import com.example.project.model.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "pay_method")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @OneToMany(mappedBy = "payMethod")
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
