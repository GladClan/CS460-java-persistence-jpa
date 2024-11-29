package edu.ensign.cs460.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByOrders_Customer_Name(String customerName);
}
