package com.simactivation.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.simactivation.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Customer findByEmailAddress(String emailAddress);
	
	Customer findByDateOfBirth(LocalDate localDate);
	Customer findByFirstName(String firstName);
	Customer findByLastName(String lastName);
	Customer findByFirstNameAndLastName(String firstName,String lastName);
}
