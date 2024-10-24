package com.gabi;

import com.gabi.customer.Customer;
import com.gabi.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.gabi")

//@Configuration

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }


    @Bean
    CommandLineRunner runner(CustomerRepository repository){

        return args ->{
            Customer alex = new Customer("alex","alex@gmail.com",34);
            Customer mihai = new Customer("mihai","mihai@gmail.com",34);
            List<Customer> customers = List.of(alex, mihai);
//            repository.saveAll(customers);
        };
    }

    @Bean
    public Foo getFoo(){
        return new Foo("bar");
    }

    record Foo(String name){

    }


}
