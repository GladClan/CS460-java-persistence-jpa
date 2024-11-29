package edu.ensign.cs460.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ensign.cs460.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Id(Long customerId);
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
    List<Order> findByStatus(String status);
    List<Order> findByProducts_Price(double price);
}
