package com.gabi.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao

{
    List<Customer> selectAllCustomer();
    Optional<Customer> getCustomerById(Integer id);
    void insertCustomer(Customer customer);
    boolean existPersonWithEmail(String email);
    boolean existsPersonwithId(Integer id);
    void deleteCustomerbyID(Integer id);
    void updateCustomer(Customer customer);
}
