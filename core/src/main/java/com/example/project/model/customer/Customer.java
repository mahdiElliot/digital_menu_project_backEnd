package com.example.project.model.customer;


import com.example.project.model.order.Order;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phoneNumber;

    @NotNull
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDTO convertToDTO() {
        return new CustomerDTO(
                id,
                name,
                email,
                phoneNumber,
                orders.stream().map(Order::convertToDTO).collect(Collectors.toSet())
        );
    }
}
