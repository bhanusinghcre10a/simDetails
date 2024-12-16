package com.simactivation.repository;

import com.simactivation.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerRowMapper implements RowMapper<CustomerDto> {


    @Override
    public CustomerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerDto customer = new CustomerDto();
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setState(rs.getString("state"));
        customer.setEmailAddress(rs.getString("email_address"));

        return customer;
    }


}
