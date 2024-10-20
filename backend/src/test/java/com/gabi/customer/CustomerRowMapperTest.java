package com.gabi.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRowMapperTest {

    private CustomerRowMapper underTest;



    @BeforeEach
    void setUp() {
        underTest= new CustomerRowMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mapRow() throws SQLException {
        //Given
        //When
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(10);
        when(resultSet.getString("name")).thenReturn("alex");
        when(resultSet.getString("email")).thenReturn("alex@gmail.com");
        when(resultSet.getInt("age")).thenReturn(19);
        Customer customer = underTest.mapRow(resultSet,1);

        //Then
        Customer expected = new Customer(10,"alex","alex@gmail.com",19);
        assertThat(customer).isEqualTo(expected);
    }
}