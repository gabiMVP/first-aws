package com.gabi.customer;

import com.gabi.AbstractTestContainerTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDAOServiceTest extends AbstractTestContainerTest {


    private CustomerJDBCDAOService underTest;
    private   CustomerRowMapper customerRowMapper = new CustomerRowMapper();


    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDAOService(
                getJdbcTemplate(),customerRowMapper
        );
    }

    @Test
    void selectAllCustomer() {
        //Given
        Customer alex = new Customer("alex","alex@gmail.com",34,"male");
        //When
        underTest.insertCustomer(alex);
        List<Customer> customers = underTest.selectAllCustomer();

        //Then
        assertThat(customers).isNotEmpty();

    }

    @Test
    void insertCustomer() {
        //Given
        Customer mihai = new Customer("mihai","mihai@gmail.com",34,"male");
        //When
        underTest.insertCustomer(mihai);
        //Then
        List<Customer> customers = underTest.selectAllCustomer();
        assertThat(customers).anyMatch(c->c.getName().equals(mihai.getName()));


    }
}