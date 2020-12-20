package com.example.project.model.menu;

import com.example.project.model.business.Business;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
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
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    public Menu() {
    }
}
