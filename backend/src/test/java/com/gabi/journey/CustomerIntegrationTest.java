package com.gabi.journey;

import com.gabi.customer.Customer;
import com.gabi.customer.CustomerRegistrationRequest;
import com.gabi.customer.CustomerUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)

public class CustomerIntegrationTest {

    private Random random = new Random();
    private final static String path = "/api/v1/customers";
    @Autowired
    private WebTestClient webTestClient;


    @Test
    void camRegisterACustomer() {

        int randomint = random.nextInt(10000);
        //create reqistration request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "alex","alex" +randomint +"@gmail.com",19,"male"
        );
        // send registration request

        webTestClient.post().uri(path).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(request),CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus().isOk();
//        get all customer
        List<Customer> allcustomers = webTestClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();
//        make sure customer is present

        Customer expectedCustomer  = new Customer(request.getName(),request.getEmail(),request.getAge(),request.getGender());

        assertThat(allcustomers)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expectedCustomer)
        ;

        int id = allcustomers.stream().filter(e->e.getEmail().equals(expectedCustomer.getEmail()))
                        .map(customer -> customer.getId())
                .findFirst().orElseThrow()
                ;


        expectedCustomer.setId(id);
           webTestClient.get()
                .uri("api/v1/customers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<Customer>() {
                }).isEqualTo(expectedCustomer);
//        get customer by id from Database

    }

    @Test
     void canDeleteCustomer(){
        int randomint = random.nextInt(10000);
        //create reqistration request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "alex","alex" +randomint +"@gmail.com",19,"male"
        );
        // send registration request

        webTestClient.post().uri(path).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(request),CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus().isOk();
//        get all customer
        List<Customer> allcustomers = webTestClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();
//        make sure customer is present


        int id = allcustomers.stream().filter(e->e.getEmail().equals(request.getEmail()))
                .map(Customer::getId)
                .findFirst().orElseThrow()
                ;


//        delete customer
        webTestClient.delete().uri("api/v1/customers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        //get customer deleted
        webTestClient.get()
                .uri("api/v1/customers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                ;

    }

    @Test
    void canUpdateCustomer(){
        int randomint = random.nextInt(10000);
        //create reqistration request
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "alex","alex" +randomint +"@gmail.com",19,"male"
        );
        // send registration request

        webTestClient.post().uri(path).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(request),CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus().isOk();
//        get all customer
        List<Customer> allcustomers = webTestClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();
//        make sure customer is present


        int id = allcustomers.stream().filter(e->e.getEmail().equals(request.getEmail()))
                .map(Customer::getId)
                .findFirst().orElseThrow()
                ;

        randomint = random.nextInt(10000);
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
                "alex3","alex" +randomint +"@gmail.com",192,"male"
        );
        // update customer with IDS email
        webTestClient.put().uri("api/v1/customers/{id}", id).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)
                . body(Mono.just(updateRequest),CustomerUpdateRequest.class)
                .exchange()
                .expectStatus().isOk();


        Customer expectedCustomerUpdated  = new Customer(id,updateRequest.name(),updateRequest.email(),updateRequest.age(),updateRequest.gender());
        //get customer with ID and check email is changed
        Customer responseBodyCustomer = webTestClient.get()
                .uri("api/v1/customers/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class).returnResult().getResponseBody();

//        get customer by id from Database
        assertThat(expectedCustomerUpdated).isEqualTo(responseBodyCustomer);

    }
}
