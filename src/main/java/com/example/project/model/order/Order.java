package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.specproduct.SpecificProduct;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "corder")
public class Order {
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

    @NotNull
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "order_sproduct",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "sproduct_id")}
    )
    @Setter
    @Getter
    private Set<SpecificProduct> specificProducts;

    @ManyToOne
    @JoinColumn(name = "paymethod_id", nullable = false)
    @Setter
    @Getter
    private PayMethod payMethod;

    public Order() {
    }

    public Order(long id, double tax, int tableNumber, Business business, Customer customer, PayMethod payMethod) {
        this.id = id;
        this.tax = tax;
        this.tableNumber = tableNumber;
        this.business = business;
        this.customer = customer;
        this.payMethod = payMethod;
    }

    public OrderDTO convertToDTO() {
        long businessId = 0;
        long customerId = 0;
        if (business != null)
            businessId = business.getId();
        if (customer != null)
            customerId = customer.getId();
        return new OrderDTO(
                id,
                tax,
                tableNumber,
                businessId,
                customerId,
                payMethod.getId()
        );
    }
}
