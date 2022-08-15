package com.simactivation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simactivation.model.CustomerIdentity;
import com.simactivation.dto.CustomerIdentityDto;
import com.simactivation.repository.CustomerIdentityRepository;

@Service
public class CustomerIdentityService {
	@Autowired
	private CustomerIdentityRepository repository;
	
	public void updateSimStatus(CustomerIdentityDto customerIdentity)
	{
		CustomerIdentity customer=repository.findByUniqueIdNumber(customerIdentity.getUniqueIdNumber());
		customer.setState("Active");
		repository.saveAndFlush(customer);
	}
	public boolean validateName(CustomerIdentityDto customerIdentity) {
		CustomerIdentity customer= repository.findByUniqueIdNumber(customerIdentity.getUniqueIdNumber());
		if(customer==null || !customer.getFirstName().equals(customerIdentity.getFirstName()) || !customer.getLastName().equals(customerIdentity.getLastName())) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean validateDob(CustomerIdentityDto customerIdentity) {
		CustomerIdentity customer= repository.findByUniqueIdNumber(customerIdentity.getUniqueIdNumber());
		if(customer.getDateOfBirth().compareTo(customerIdentity.getDateOfBirth())==0) {
			return true;
		}
		else {
			return false;
		}
	}
}
