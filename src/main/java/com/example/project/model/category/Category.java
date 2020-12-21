package com.example.project.model.category;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    public Category() {
    }

    public Category(long id, String name, int rank, boolean enabled, String image, Business business) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.enabled = enabled;
        this.image = image;
        this.business = business;
    }
}
