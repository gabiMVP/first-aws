package com.gabi.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAceessService implements CustomerDao{

    private static List<Customer> customers;

    static{
        customers = new ArrayList<>();
        Customer alex = new Customer(1,"alex","alex@gmail.com",34,"male");
        Customer mihai = new Customer(2,"mihai","alex@gmail.com",34,"male");
        customers.add(alex);
        customers.add(mihai);

    }

    @Override
    public List<Customer> selectAllCustomer() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return   customers.stream().filter(customer -> customer.getId().equals(id))
                .findFirst();

    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonwithId(Integer id) {
        return customers.stream().anyMatch(c ->c.getId().equals(id));
    }

    @Override
    public void deleteCustomerbyID(Integer id) {
        Optional<Customer> customer = getCustomerById(  id);
        customer.ifPresent(c -> customers.remove(c));
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }


}
