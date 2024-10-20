package com.gabi.customer;

import com.gabi.AbstractTestContainerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestContainerTest {

    @Autowired
    private CustomerRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;
    @BeforeEach
    void setUp() {
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsCustomerByEmail() {
        //Given
        Customer alex = new Customer("gigica","gigica@gmail.com",34);
        //When
        underTest.save(alex);
        //Then
        assertThat(underTest.existsCustomerByEmail(alex.getEmail())).isTrue();


    }
    @Test
    void existsCustomerByEmailFailsWhenUnknownMail() {
        //Given
        Customer alex = new Customer("gigica","gigica@gmail.com",34);
        //When

        assertThat(underTest.existsCustomerByEmail("mirica@gmail.com")).isFalse();



    }
}