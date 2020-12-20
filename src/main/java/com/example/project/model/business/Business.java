package com.example.project.model.business;

import com.example.project.model.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "business")
public class Business {
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
    private Double serviceFee;

    @Setter
    @Getter
    private Double tax;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private String logo;

    @OneToMany(mappedBy = "business")
    @Setter
    @Getter
    private Set<Menu> menus;

    public Business() {
    }

    public Business(long id, String name, double serviceFee, double tax, String logo, Set<Menu> menus) {
        this.id = id;
        this.name = name;
        this.serviceFee = serviceFee;
        this.tax = tax;
        this.logo = logo;
        this.menus = menus;
    }
}
