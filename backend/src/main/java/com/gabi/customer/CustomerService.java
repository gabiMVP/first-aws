package com.gabi.customer;

import com.gabi.exception.DuplicateResourceException;
import com.gabi.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomer();
    }
    public Customer getCustomerByID(Integer id ){
        return customerDao.getCustomerById(id).orElseThrow(
                () -> new ResourceNotFound("customer with id [%s] not found".formatted(id))

        );
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
//        check is email exist
        if(customerDao.existPersonWithEmail(customerRegistrationRequest.email)){
            throw new DuplicateResourceException(
                    "email already exists"
            );
        }
        customerDao.insertCustomer(new Customer(
                customerRegistrationRequest.name,
                customerRegistrationRequest.email,
                customerRegistrationRequest.age
        ));
    }

    public void deleteCustomer(Integer id){
        customerDao.deleteCustomerbyID(id);
    }

    public void updateCustomer(Integer customerID,CustomerUpdateRequest customerUpdateRequest){
        Customer customer =  getCustomerByID(  customerID );
        boolean changes= false;
        if(customerUpdateRequest.name()!=null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            changes = true;
        }
        if(customerUpdateRequest.email()!=null && !customerUpdateRequest.email().equals(customer.getEmail())){
            customer.setEmail(customerUpdateRequest.email());
            changes = true;
        }
        if(customerUpdateRequest.age()!=null && !customerUpdateRequest.age().equals(customer.getAge())) {
            customer.setAge(customerUpdateRequest.age());
            changes = true;
        }
        if(!changes){
            throw new DuplicateResourceException("no changes");
        }

        customerDao.updateCustomer(customer);

    }

}
