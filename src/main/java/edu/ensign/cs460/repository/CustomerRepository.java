package edu.ensign.cs460.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Customer;

/**
 * Repository for managing Customer entities.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Find customers by name.
     * @param name The name to search for.
     * @return A list of customers with the given name.
     */
    List<Customer> findByName(String name);

    /**
     * Find a customer by email.
     * @param email The email to search for.
     * @return The customer with the given email, if found.
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Find customers by the status of their orders.
     * @param status The status to search for.
     * @return A list of customers with orders in the given status.
     */
    List<Customer> findByOrdersStatus(String status);
}
