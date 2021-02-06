package com.example.project.model.purchase;

import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "purchase")
@TypeDef(typeClass = JsonBinaryType.class, name = "jsonb")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_generator")
    @SequenceGenerator(name = "purchase_generator", sequenceName = "purchase_seq", allocationSize = 1)
    private Long id;

    private String comment;

    @Column(nullable = false)
    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String jsonOptions;

    public Purchase(long id, String comment, int quantity, double price, Product product, Order order) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }

    public PurchaseDTO convertToDTO() {
        return new PurchaseDTO(id, comment, quantity, price, product.getId(), order.getId(), jsonOptions);
    }
}
