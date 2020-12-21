package com.example.project.model.extra;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "extra")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false)
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    public Extra() {
    }

    public Extra(long id, String name, String description, boolean enabled, Business business) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.business = business;
    }
}
