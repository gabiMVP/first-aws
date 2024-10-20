package com.gabi.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJDBCDAOService implements CustomerDao{


    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDAOService(JdbcTemplate jdbcTemplate,CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        var sql = """
                Select id,name,email, age from customer
                """;

        List<Customer> customers = jdbcTemplate.query(sql,customerRowMapper);

        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name, email, age) Values (?,?,?)
                """;
        int update = jdbcTemplate.update(sql,customer.getName(),customer.getEmail(),customer.getAge());
        System.out.println("Insert customer =" + update);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return false;
    }

    @Override
    public boolean existsPersonwithId(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomerbyID(Integer id) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }
}
