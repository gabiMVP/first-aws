package com.gabi.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/customers" )
public class CustomerControler {

    private final CustomerService customerService;

    public CustomerControler(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    @RequestMapping(path = "api/v1/customer", method = RequestMethod.GET)
    @GetMapping
    public  List<Customer> getCustomers() {
        return customerService.getAllCustomers() ;
    }
    @GetMapping(path ="/{customerId}" )
    public Customer getCustomer(@PathVariable("customerId") Integer customerID){

        System.out.println("TOP TOP" + customerID);
        return customerService.getCustomerByID(customerID);
    }
    @PostMapping
    public void addCustomer( @RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request);
    }

    @DeleteMapping(path ="/{customerId}" )
    public void deleteCustomer(@PathVariable("customerId") Integer customerID){
        customerService.deleteCustomer(customerID);
    }
    @PutMapping(path ="/{customerId}" )
    public void deleteCustomer(@PathVariable("customerId") Integer customerID,@RequestBody CustomerUpdateRequest request){
        customerService.updateCustomer(customerID,request);
    }
}
