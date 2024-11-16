package edu.ensign.cs460.autograder;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test configuration class for setting up the context for repository tests.
 */
@SpringBootConfiguration
@EnableJpaRepositories(basePackages = "edu.ensign.cs460.repository")
@EntityScan(basePackages = "edu.ensign.cs460.model")
public class TestConfig {
    // Additional beans or configurations can be defined here if needed

    // This class can be left empty if @EnableJpaRepositories is the only requirement
}

