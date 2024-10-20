package com.gabi.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomerJPADataAccessServiceTest {

    private CustomerJPADataAccessService underTest;

    @Mock
    private CustomerRepository customerRepository;
    private  AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void selectAllCustomer() {

        //When
        underTest.selectAllCustomer();
        //Then
        verify(customerRepository)
                . findAll();
    }

    @Test
    void getCustomerById() {
        //Given
        int id = 1;
        //When
        underTest.getCustomerById(id);
        //Then
        verify(customerRepository).findById(id);
    }

    @Test
    void insertCustomer() {
        //Given
        //When

        //Then
    }

    @Test
    void existPersonWithEmail() {
        //Given
        //When

        //Then
    }

    @Test
    void existsPersonwithId() {
        //Given
        //When

        //Then
    }

    @Test
    void deleteCustomerbyID() {
        //Given
        //When

        //Then
    }

    @Test
    void updateCustomer() {
        //Given
        //When

        //Then
    }
}