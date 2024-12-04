package edu.ensign.cs460.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Represents an order placed by a customer.
 */
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "orders")
public class Order {
    /**
     * The unique identifier of the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The date the order was placed.
     */
    @Column(name = "orderDate")
    private Date orderDate;

    /**
     * The status of the order.
     */
    @Column(name = "status")
    private String status;

    /**
     * The customer who placed the order.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    /**
     * The products included in the order.
     */
    @ManyToMany(mappedBy = "orders")
    private List<Product> products;
}
