package com.example.project.model.customer;


import com.example.project.model.menu.Menu;
import com.example.project.model.order.COrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    @Setter
    @Getter
    private Set<COrder> orders;

    public Customer(){}

    public Customer(Long id, String name, String email, String phoneNumber, Set<COrder> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
    }
}
