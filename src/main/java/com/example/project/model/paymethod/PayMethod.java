package com.example.project.model.paymethod;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "business_paymethod",
            joinColumns = {@JoinColumn(name = "paymethod_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_id")}
    )
    @Setter
    @Getter
    Set<Business> businesses;

    public PayMethod() {
    }

    public PayMethod(long id, String data, String name, boolean enabled, Set<Business> businesses) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.enabled = enabled;
        this.businesses = businesses;
    }
}
