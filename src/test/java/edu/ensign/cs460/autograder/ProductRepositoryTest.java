package edu.ensign.cs460.autograder;

import edu.ensign.cs460.model.Product;
import edu.ensign.cs460.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ProductRepository.
 */
@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Test saving a Product to the database.
     * Ensures that the product is persisted and assigned an ID.
     */
    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(999.99);
        product.setDescription("High-performance laptop");
        productRepository.save(product);

        assertNotNull(product.getId(), "Product ID should be generated");
        assertEquals("Laptop", product.getName());
        assertEquals(999.99, product.getPrice());
    }

    /**
     * Test finding products by name.
     * Verifies that products with a specific name can be retrieved.
     */
    @Test
    public void testFindByName() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(799.99);
        product.setDescription("Latest smartphone model");
        productRepository.save(product);

        List<Product> products = productRepository.findByName("Smartphone");
        assertEquals(1, products.size(), "There should be one product named 'Smartphone'");
        assertEquals("Smartphone", products.get(0).getName());
    }

    /**
     * Test finding products by price less than a specified value.
     * Ensures that products below a certain price threshold are retrieved.
     */
    @Test
    public void testFindByPriceLessThan() {
        Product product1 = new Product();
        product1.setName("Headphones");
        product1.setPrice(199.99);

        Product product2 = new Product();
        product2.setName("Keyboard");
        product2.setPrice(49.99);

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findByPriceLessThan(100.00);
        assertEquals(1, products.size(), "There should be one product priced below $100.00");
        assertEquals("Keyboard", products.get(0).getName());
    }
}
