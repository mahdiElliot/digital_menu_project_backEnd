package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "corder")
public class COrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Double tax;

    @Column(nullable = false, unique = true)
    @Setter
    @Getter
    private Integer tableNumber;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    @Setter
    @Getter
    private Business business;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter
    @Getter
    private Customer customer;

    public COrder() {
    }

    public COrder(long id, double tax, int tableNumber, Business business) {
        this.id = id;
        this.tax = tax;
        this.tableNumber = tableNumber;
        this.business = business;
    }
}
