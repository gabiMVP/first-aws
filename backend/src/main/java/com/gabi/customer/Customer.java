package com.gabi.customer;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer",
uniqueConstraints = {
        @UniqueConstraint(name="customer_id_unique",columnNames = "email")
}
)
public  class Customer{
    @Id
    @SequenceGenerator(
            name="customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;

    private String name;
    @Column(
            nullable = false,
            unique = true
    )

    private String email;
    @Column(
            nullable = false
    )
    private int age;

    private String gender;


    public Customer() {
    }

    public Customer(Integer id,String name, String email, int age,String gender) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public Customer(String name, String email, int age,String gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender =gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(gender, customer.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, gender);
    }
}