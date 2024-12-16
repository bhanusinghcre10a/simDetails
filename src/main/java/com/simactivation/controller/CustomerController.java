package com.simactivation.controller;

import com.simactivation.handler.GlobalHandler;
import com.simactivation.repository.CustomerRepositoryExtended;
import com.simactivation.util.ConfigFactory;
import org.springframework.context.ApplicationContext;
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
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.simactivation.ErrorsMessage;
import com.simactivation.NoSuchCustomerAddressException;
import com.simactivation.NoSuchCustomerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/customer")
public class CustomerController{


	@Autowired
	private ApplicationContext context;
	@Autowired
	CustomerIdentityService CustomerIdentityservice;
	@Autowired
	CustomerAddressService CustomerAddressService;
	@Autowired
	CustomerService CustomerService;

	@Autowired
	ConfigFactory configFactory ;

	@Autowired
	CustomerRepositoryExtended customerRepositoryExtended;


	@GetMapping("/Config")
	public ModelAndView getConfig(@RequestParam("config") String config) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		ConfigFactory configFactory = context.getBean(ConfigFactory.class);
		ModelAndView mv =  new ModelAndView();
		mv.setViewName("index");
		mv.addObject("config",configFactory.get(config));
		return mv;
	}

    @GetMapping("/config")
    public ResponseEntity<Object> getconfig(@RequestParam("config") String config) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ConfigFactory configFactory = context.getBean(ConfigFactory.class);
        return ResponseEntity.ok(configFactory.get(config));
    }

	@GetMapping("/customers")
	public ResponseEntity<Object> getCustomers() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		return ResponseEntity.ok(customerRepositoryExtended.findUsers());
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCustomer(@RequestBody CustomerDto customer) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		try {
			CustomerService.addCustomer(customer);
		}catch (Exception e) {
			return ResponseEntity.ok("something went wrong");
		}
		return ResponseEntity.ok("added");
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