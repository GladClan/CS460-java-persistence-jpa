[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=17288312)
# Spring Data JPA Persistence Assignment

For this assignment, we’ll create a simplified **e-commerce model** with three main entities:

- **Customer**
- **Order**
- **Product**

This model will allow us to explore core concepts of Spring Data JPA, including **CRUD operations**, **relationships between entities**, and **custom queries**.

---

## 📊 Database Tables and Relationships

### Customer
- **Table**: `customers`
- **Attributes**:
  - `id`: Primary key, `Long`
  - `name`: Customer's name, `String`
  - `email`: Customer's email, `String` (unique)
  - `orders`: A list of orders associated with the customer (one-to-many relationship)
- **Relationships**:
  - **One-to-Many with Orders**: Each customer can have multiple orders.

### Order
- **Table**: `orders`
- **Attributes**:
  - `id`: Primary key, `Long`
  - `orderDate`: Date of the order, `Date`
  - `status`: Status of the order (e.g., “PENDING,” “SHIPPED,” “DELIVERED”), `String`
  - `customer`: The customer who placed the order (many-to-one relationship)
  - `products`: List of products associated with this order (many-to-many relationship)
- **Relationships**:
  - **Many-to-One with Customer**: Each order is linked to one customer.
  - **Many-to-Many with Products**: Each order can contain multiple products, and each product can be part of multiple orders.

### Product
- **Table**: `products`
- **Attributes**:
  - `id`: Primary key, `Long`
  - `name`: Product name, `String`
  - `price`: Product price, `Double`
  - `description`: Product description, `String`
  - `orders`: List of orders that include this product (many-to-many relationship)
- **Relationships**:
  - **Many-to-Many with Orders**: Each product can belong to multiple orders, and each order can have multiple products.

---

## 📂 Repository Functionality

Below are the expected repository interfaces and their core functionality. These repositories will be implemented using **Spring Data JPA**.

### CustomerRepository
- **Interface**: `CustomerRepository extends JpaRepository<Customer, Long>`
- **Core Methods**:
  - `save(Customer customer)`
  - `findById(Long id)`
  - `deleteById(Long id)`
  - `findAll()`
- **Custom Methods**:
  - `List<Customer> findByName(String name)`
  - `Optional<Customer> findByEmail(String email)`
  - `List<Customer> findByOrders_Status(String status)`

### OrderRepository
- **Interface**: `OrderRepository extends JpaRepository<Order, Long>`
- **Core Methods**:
  - `save(Order order)`
  - `findById(Long id)`
  - `deleteById(Long id)`
  - `findAll()`
- **Custom Methods**:
  - `List<Order> findByCustomer_Id(Long customerId)`
  - `List<Order> findByOrderDateBetween(Date startDate, Date endDate)`
  - `List<Order> findByStatus(String status)`
  - `List<Order> findByProducts_Name(String productName)`

### ProductRepository
- **Interface**: `ProductRepository extends JpaRepository<Product, Long>`
- **Core Methods**:
  - `save(Product product)`
  - `findById(Long id)`
  - `deleteById(Long id)`
  - `findAll()`
- **Custom Methods**:
  - `List<Product> findByName(String name)`
  - `List<Product> findByPriceLessThan(Double price)`
  - `List<Product> findByOrders_Customer_Name(String customerName)`

---

## 🔍 Summary of Expected Repository Functionalities

This setup will allow you to:

- **Query by Relationships**: Retrieve customers based on their orders, products based on who ordered them, etc.
- **Apply Filtering and Range Queries**: Use criteria like `orderDate` ranges and `price` filters to retrieve targeted data.
- **Navigate Associations**: Access products through orders and vice versa, supporting complex data retrieval for realistic scenarios.

Each repository should be **fully tested using JUnit** to confirm functionality and ensure reliable data operations.

---

## Assignment Instructions

### Step 1: Accept the GitHub Classroom Assignment
👉 Click on the **Java Persistence JPA** link to accept the GitHub Classroom assignment.

### Step 2: Configure Database Settings
In `application.properties` (or `application.yml`), configure the database settings. Here’s an example for an H2 database setup:

**`src/test/resources/application.properties`**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

### Step 3: Define Your Domain Model (Entity Classes)
Create JPA entity classes for Customer, Order, and Product.

Entity Annotations: Use @Entity, @Table, @Id, and other annotations. Example:
```java
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // getters and setters
}
```
Relationships: Define relationships using @OneToMany, @ManyToOne, @OneToOne, and @ManyToMany.
```java
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date orderDate;
    // additional fields, getters, setters
}
```
### Step 4: Implement Repositories
Create Repository Interfaces: Each entity should have a corresponding repository interface extending JpaRepository.
Example
```java
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
}
```
Custom Queries: Define additional query methods as needed using method names, JPQL, or @Query.

### Step 5: Add Unit Tests for Repository Methods
Write tests using JUnit to validate each repository’s functionality.

Example:

```java

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByName() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findByName("John Doe");
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
    }
}
```
Use @Transactional in tests when methods involve multiple steps or relationships.

### Step 6: Add Documentation and Comments
Add JavaDoc comments to each class and method to clarify their purpose.
Document any complex queries, design choices, or data modeling decisions.

### Step 7: Run and Verify
Run All Tests: Ensure all repository tests pass.
Check the Database Console: If using H2, visit the console and verify your tables and data.

