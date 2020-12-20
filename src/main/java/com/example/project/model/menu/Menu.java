package com.example.project.model.menu;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
//    @SequenceGenerator(name = "menu_generator", sequenceName = "menu_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Column(nullable = false, unique = true)
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

    public Menu() {
    }

    public Menu(Long id, String name, boolean enabled, Business business) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.business = business;
    }
}
