package edu.ensign.cs460.autograder;

import edu.ensign.cs460.model.Order;
import edu.ensign.cs460.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for OrderRepository.
 */
@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Test saving an Order to the database.
     * Ensures that the order is persisted and assigned an ID.
     */
    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus("PENDING");
        orderRepository.save(order);

        assertNotNull(order.getId(), "Order ID should be generated");
        assertEquals("PENDING", order.getStatus());
    }

    /**
     * Test finding orders by status.
     * Verifies that orders with a specific status can be retrieved.
     */
    @Test
    public void testFindByStatus() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus("SHIPPED");
        orderRepository.save(order);

        List<Order> orders = orderRepository.findByStatus("SHIPPED");
        assertEquals(1, orders.size(), "There should be one order with status 'SHIPPED'");
    }

    /**
     * Test finding orders within a specific date range.
     * Ensures that the repository retrieves the correct orders based on date criteria.
     */
    @Test
    public void testFindByOrderDateBetween() {
        Order order1 = new Order();
        order1.setOrderDate(new Date(System.currentTimeMillis() - 86400000L)); // Yesterday
        order1.setStatus("PENDING");

        Order order2 = new Order();
        order2.setOrderDate(new Date());
        order2.setStatus("PENDING");

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findByOrderDateBetween(
                new Date(System.currentTimeMillis() - 2 * 86400000L), // Two days ago
                new Date()
        );
        assertEquals(2, orders.size(), "There should be two orders within the date range");
    }
}

