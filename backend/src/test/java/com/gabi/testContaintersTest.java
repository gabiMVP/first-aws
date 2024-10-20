package com.gabi;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

public class testContaintersTest extends AbstractTestContainerTest{


    @Test
    void canStartPostresDB() {
        assertThat(posgressContainer.isRunning()).isTrue();
        assertThat(posgressContainer.isCreated()).isTrue();

    }
}
