
package com.simactivation.service;
import com.simactivation.repository.CustomerRepositoryExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simactivation.dto.CustomerDto;
import com.simactivation.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;

	@Autowired
	CustomerRepositoryExtended customerRepositoryExtended;
	public boolean validateDobAndEmail(CustomerDto customer)
	{
		if(repository.findByDateOfBirth(customer.getDateOfBirth())!=null && repository.findByEmailAddress(customer.getEmailAddress())!=null) {
			
			return true;			
		}
		else {
			return false;
		}
	}
	public boolean validateFirstAndLastName(CustomerDto customer)
	{
		if(repository.findByFirstName(customer.getFirstName())!=null && repository.findByLastName(customer.getLastName())!=null) {
			return true;
			
		}
		else {
			return false;
		}
	}
	public boolean validateEmailAddress(CustomerDto customer)
	{
		if(repository.findByFirstNameAndLastName(customer.getFirstName(),customer.getLastName()).getEmailAddress().equals(customer.getEmailAddress())) {
			return true;
			
		}
		else {
			return false;
		}
	}

	public void addCustomer(CustomerDto customer) {
		customerRepositoryExtended.addUser(customer);
	}
}
