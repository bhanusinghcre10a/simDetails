package com.simactivation.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simactivation.model.CustomerAddress;
import com.simactivation.NoSuchCustomerAddressException;
import com.simactivation.dto.CustomerAddressDto;
import com.simactivation.repository.CustomerAddressRepository;
@Service
public class CustomerAddressService {

	@Autowired
	private CustomerAddressRepository repository;
	
	public void updateAddress(CustomerAddressDto customerAddress) throws NoSuchCustomerAddressException
	{

		CustomerAddress customer=repository.findByAddressId(customerAddress.getAddressId());
		if(customer==null) {
			throw new NoSuchCustomerAddressException("address not available for this details");
		}
		customer.setAddress(customerAddress.getAddress());
		customer.setPincode(customerAddress.getPincode());
		customer.setCity(customerAddress.getCity());
		customer.setState(customerAddress.getState());
		repository.saveAndFlush(customer);
	}
	
}
