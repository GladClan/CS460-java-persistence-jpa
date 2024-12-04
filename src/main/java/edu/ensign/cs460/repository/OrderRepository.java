package edu.ensign.cs460.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Order;

/**
 * Repository interface for Order entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Finds orders by the ID of the customer who placed them.
     * @param customerId the ID of the customer
     * @return a list of orders placed by the customer
     */
    List<Order> findByCustomerId(Long customerId);

    /**
     * Finds orders by the date they were placed.
     * @param startDate the start date
     * @param endDate the end date
     * @return a list of orders placed between the start and end dates
     */
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    /**
     * Finds orders by their status.
     * @param status the status of the order
     * @return a list of orders with the given status
     */
    List<Order> findByStatus(String status);

    /**
     * Finds orders by the price of the products they contain.
     * @param price the price of the products
     * @return a list of orders with products of the given price
     */
    List<Order> findByProductsPrice(double price);
}
