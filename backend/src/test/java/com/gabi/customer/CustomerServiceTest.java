package com.gabi.customer;

import com.gabi.exception.DuplicateResourceException;
import com.gabi.exception.ResourceNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService underTest;
    @Mock
    private CustomerDao customerDao;



    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerDao);
    }


    @Test
    void getAllCustomers() {
        //Given
        //When
        underTest.getAllCustomers();
        //Then
        verify(customerDao).selectAllCustomer();
    }

    @Test
    void getCustomerByID() {
        //Given
        //When
        int id  = 10;
        Customer customer = new Customer(id,"alex","alex@gmail.com",20,"male");

        //Then
        when(customerDao.getCustomerById(id)).thenReturn(Optional.of(customer));

        Customer customerByID = underTest.getCustomerByID(id);

        assertThat(customerByID).isEqualTo(customer);
    }

    @Test
    void wilThrowWhenCustomerThrowException() {
        //Given
        //When
        int id  = 10;
        Customer customer = new Customer(id,"alex","alex@gmail.com",20,"male");

        //Then
        when(customerDao.getCustomerById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(()->underTest.getCustomerByID(id))
                .isInstanceOf(ResourceNotFound.class).
                hasMessageContaining("customer with id [%s] not found".formatted(id));

//        Customer customerByID = underTest.getCustomerByID(id);
//
//        assertThat(customerByID).isEqualTo(customer);
    }

    @Test
    void addCustomer() {
        //Given
        String mail = "alex@gmail.com";

        when(customerDao.existPersonWithEmail(mail)).thenReturn(false);


        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                "alex", mail,20,"male");



        //When
        underTest.addCustomer(customerRegistrationRequest);

        //Then
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

        Customer captured = customerArgumentCaptor.getValue();

        assertThat(captured.getId()).isNull();
        assertThat(captured.getName()).isEqualTo(customerRegistrationRequest.getName());
        assertThat(captured.getEmail()).isEqualTo(customerRegistrationRequest.getEmail());
        assertThat(captured.getAge()).isEqualTo(customerRegistrationRequest.getAge());

    }
    @Test
    void addCustomerIfEmailTakenThrowException() {
        //Given
        String mail = "alex@gmail.com";

        when(customerDao.existPersonWithEmail(mail)).thenReturn(true);


        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                "alex", mail,20,"male");



        //When
        assertThatThrownBy(()->underTest.addCustomer(customerRegistrationRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("already");

        verify(customerDao,never()).insertCustomer(any());
    }

    @Test
    void deleteCustomer() {
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