package edu.ensign.cs460.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    Optional<Customer> findByEmail(String email);
    List<Customer> findByOrders_Status(String status);
}
