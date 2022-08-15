package com.simactivation.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import com.simactivation.util.AddressMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import com.simactivation.dto.CustomerDto;
import com.simactivation.dto.CustomerIdentityDto;
import com.simactivation.dto.CustomerAddressDto;

import com.simactivation.service.CustomerIdentityService;
import com.simactivation.service.CustomerAddressService;
import com.simactivation.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.simactivation.ErrorsMessage;
import com.simactivation.NoSuchCustomerAddressException;
import com.simactivation.NoSuchCustomerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/customer")
public class CustomerController{
	
	
	
	@Autowired
	CustomerIdentityService CustomerIdentityservice;
	@Autowired
	CustomerAddressService CustomerAddressService;
	@Autowired
	CustomerService CustomerService;

	@GetMapping("/Config")
	public ResponseEntity<Object> getConfic() throws IOException {
		AddressMapper addressMapper = new AddressMapper();
		return ResponseEntity.ok(addressMapper.readConfig());
	}
	
	@PostMapping("/details/basic")
    public ResponseEntity<Object> postBasicDetails(@Valid @RequestBody CustomerDto customer,BindingResult errors){
		String response = "";
        if(errors.hasErrors()) {
        	response = errors.getAllErrors().stream().map(e->e.getDefaultMessage())
					.collect(Collectors.joining(","));
        	ErrorsMessage error =new ErrorsMessage();
        	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
        	error.setMessage(response);
        	 return ResponseEntity.ok(error);
        }
        else {
        	if(!CustomerService.validateDobAndEmail(customer)) {
        		ErrorsMessage error =new ErrorsMessage();
            	error.setErrorCode(HttpStatus.NOT_FOUND.value());
            	error.setMessage("No request placed for you");
            	 return ResponseEntity.ok(error);
        	}
        	else {
        		 return ResponseEntity.ok("success");
        	}
        }
    }
	
	
	@PostMapping("/details/personal")
    public ResponseEntity<Object> postPersonalDetails(@Valid @RequestBody CustomerDto customer,BindingResult errors) throws NoSuchCustomerException{
		String response = "";
        if(errors.hasErrors()) {
        	response = errors.getAllErrors().stream().map(e->e.getDefaultMessage())
					.collect(Collectors.joining(","));
        	ErrorsMessage error =new ErrorsMessage();
        	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
        	error.setMessage(response);
        	 return ResponseEntity.ok(error);
        }
        else {
        	if(CustomerService.validateFirstAndLastName(customer)) {
        		if(CustomerService.validateEmailAddress(customer)) {
        			return ResponseEntity.ok("success");
        		}
        		else {
        			ErrorsMessage error =new ErrorsMessage();
                	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
                	error.setMessage("Invalid email details!!");
                	 return ResponseEntity.ok(error);
        		}
        	}
        	else {
            	ErrorsMessage error =new ErrorsMessage();
            	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
            	error.setMessage("No customer found for the provided details");
            	 return ResponseEntity.ok(error);
        		
        	}
        }
    }
	
	
	@PutMapping("/address")
    public ResponseEntity<Object> updateAddressDetails(@Valid @RequestBody CustomerAddressDto customer, BindingResult errors) throws NoSuchCustomerAddressException{
		String response = "";
        if(errors.hasErrors()) {
        	response = errors.getAllErrors().stream().map(e->e.getDefaultMessage())
					.collect(Collectors.joining(","));
        	ErrorsMessage error =new ErrorsMessage();
        	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
        	error.setMessage(response);
        	 return ResponseEntity.ok(error);
        }
        else {
        	CustomerAddressService.updateAddress(customer);
        	return ResponseEntity.ok("address updated successfully");
        }
        
	}
	
	@PostMapping(value="/idproof",consumes="application/json")
    public ResponseEntity<Object>  postpersonaldetails(@Valid @RequestBody CustomerIdentityDto customerIdentity,BindingResult errors){
		String response = "";
        if(errors.hasErrors()) {
        	response = errors.getAllErrors().stream().map(e->e.getDefaultMessage())
					.collect(Collectors.joining(","));
        	ErrorsMessage error =new ErrorsMessage();
        	error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
        	error.setMessage(response);
        	 return ResponseEntity.ok(error);
        }
        else {
        	
        	if(!CustomerIdentityservice.validateName(customerIdentity)) {
        		ErrorsMessage error =new ErrorsMessage();
            	error.setErrorCode(HttpStatus.NOT_FOUND.value());
            	error.setMessage("Invalid details");
            	return ResponseEntity.ok(error);
        	}
        	else if(!CustomerIdentityservice.validateDob(customerIdentity)) {
        		
        		ErrorsMessage error =new ErrorsMessage();
            	error.setErrorCode(HttpStatus.NOT_FOUND.value());
            	error.setMessage("Incorrect date of birth details");
            	return ResponseEntity.ok(error);
        	}
        	else {
        		CustomerIdentityservice.updateSimStatus(customerIdentity);
        		return ResponseEntity.ok("sim updated succesfully");
        	}
        }
    }
}