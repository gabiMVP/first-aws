package com.gabi;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
@Testcontainers
public abstract class AbstractTestContainerTest {

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure().dataSource(
                posgressContainer.getJdbcUrl(),posgressContainer.getUsername(),posgressContainer.getPassword()
        ).load();
        flyway.migrate();
        System.out.println("plop");
    }

    @Container
    protected static final PostgreSQLContainer<?> posgressContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("gabi-dao-unit-test").withUsername("username")
                    .withPassword("password");




    @DynamicPropertySource
    protected static void registerDataSourceProperties(DynamicPropertyRegistry registry){
        registry.add(
                "spring.datasource.url", posgressContainer::getJdbcUrl
        );
        registry.add(
                "spring.datasource.username", posgressContainer::getUsername
        );
        registry.add(
                "spring.datasource.password", posgressContainer::getPassword
        );
    }

    private static DataSource getDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create().
                driverClassName(posgressContainer.getDriverClassName())
                .url(posgressContainer.getJdbcUrl())
                .username(posgressContainer.getUsername())
                .password(posgressContainer.getPassword());
         return builder.build();
    }

    public JdbcTemplate getJdbcTemplate(){
        return  new JdbcTemplate(getDataSource());
    }

}
