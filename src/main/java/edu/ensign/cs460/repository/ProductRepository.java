package edu.ensign.cs460.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Product;

/**
 * Repository interface for Product entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds products by their name.
     * @param name the name of the product
     * @return a list of products with the given name
     */
    List<Product> findByName(String name);

    /**
     * Finds products by their price.
     * @param price the price of the product
     * @return a list of products with the given price
     */
    List<Product> findByPriceLessThan(double price);

    /**
     * Finds products by the name of the customer who ordered them.
     * @param customerName the name of the customer
     * @return a list of products ordered by the customer
     */
    List<Product> findByOrdersCustomerName(String customerName);
}
