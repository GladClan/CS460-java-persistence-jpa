package edu.ensign.cs460.autograder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import edu.ensign.cs460.model.Customer;
import edu.ensign.cs460.repository.CustomerRepository;

/**
 * Unit tests for CustomerRepository.
 */
@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Test saving a Customer to the database.
     * Ensures that the customer is persisted and assigned an ID.
     */
    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customerRepository.save(customer);

        assertNotNull(customer.getId(), "Customer ID should be generated");
        assertEquals("John Doe", customer.getName());
    }

    /**
     * Test finding a customer by their name.
     * Verifies the repository method retrieves the correct customer.
     */
    @Test
    public void testFindByName() {
        Customer customer = new Customer();
        customer.setName("Jane Smith");
        customer.setEmail("jane.smith@example.com");
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findByName("Jane Smith");
        assertEquals(1, customers.size(), "There should be one customer with the name 'Jane Smith'");
        assertEquals("Jane Smith", customers.get(0).getName());
    }

    /**
     * Test finding a customer by their email.
     * Validates that the repository can locate a customer with a unique email.
     */
    @Test
    public void testFindByEmail() {
        Customer customer = new Customer();
        customer.setName("Bob Brown");
        customer.setEmail("bob.brown@example.com");
        customerRepository.save(customer);

        assertTrue(customerRepository.findByEmail("bob.brown@example.com").isPresent(),
                "Customer with email 'bob.brown@example.com' should exist");
    }
}
