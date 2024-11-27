package edu.ensign.cs460.model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(mappedBy = "orders")
    private List<Product> products;
}
