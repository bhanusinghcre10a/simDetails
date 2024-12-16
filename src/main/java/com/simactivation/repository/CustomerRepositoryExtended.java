package com.simactivation.repository;

import com.simactivation.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Random;

public class CustomerRepositoryExtended {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<CustomerDto> findUsers(){
        String query = "SELECT * FROM customer";
        List<CustomerDto> customer = jdbcTemplate.query(query,  new CustomerRowMapper() ,new Object[] { });
        return customer;
    }

    public void addUser(CustomerDto customer){
        jdbcTemplate.update("INSERT INTO customer(sim_id,customer_address_address_id,unique_id_number,first_name,last_name,state,email_address) VALUES (?, ?, ?, ?, ?, ?, ?)", customer.getSimId(),customer.getCustomerAddress_addressId(),customer.getUniqueIdNumber(),customer.getFirstName(), customer.getLastName(), customer.getState(),customer.getEmailAddress());
    }
}
