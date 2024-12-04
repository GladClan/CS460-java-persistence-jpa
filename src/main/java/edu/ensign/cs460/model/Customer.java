package edu.ensign.cs460.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * Entity class for customers.
 */
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "customers")
public class Customer {
    /**
     * The unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the customer.
     */
    @Column(name = "name")
    private String name;

    /**
     * The email address of the customer.
     */
    @Column(name = "email")
    private String email;

    /**
     * The orders placed by the customer.
     */
    @Column
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
