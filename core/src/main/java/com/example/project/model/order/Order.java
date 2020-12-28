package com.example.project.model.order;

import com.example.project.model.business.Business;
import com.example.project.model.customer.Customer;
import com.example.project.model.paymethod.PayMethod;
import com.example.project.model.specproduct.SpecificProduct;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "corder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double tax;

    private String comment;

    @Column(nullable = false, unique = true)
    private Integer tableNumber;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "order_sproduct",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "sproduct_id")}
    )
    private Set<SpecificProduct> specificProducts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "paymethod_id", nullable = false)
    private PayMethod payMethod;

    public Order() {
    }

    public Order(long id, double tax, int tableNumber, String comment, Business business, Customer customer, PayMethod payMethod) {
        this.id = id;
        this.tax = tax;
        this.tableNumber = tableNumber;
        this.business = business;
        this.customer = customer;
        this.payMethod = payMethod;
        this.comment = comment;
    }

    public OrderDTO convertToDTO() {
        Long businessId = null;
        Long customerId = null;
        if (business != null)
            businessId = business.getId();
        if (customer != null)
            customerId = customer.getId();
        return new OrderDTO(
                id,
                tax,
                tableNumber,
                comment,
                businessId,
                customerId,
                payMethod.getId()
        );
    }
}
