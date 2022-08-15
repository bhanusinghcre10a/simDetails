package com.simactivation.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import com.simactivation.ErrorsMessage;
import com.simactivation.service.CustomerSimDetailsService;

import com.simactivation.dto.SimDetailsDto;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/customer")
public class SimDetailsController {
		
		@Autowired
		CustomerSimDetailsService CustomerSimDetailsService;
		
		@PostMapping("/sim")
	    public ResponseEntity<Object> postBasicDetails(@Valid @RequestBody SimDetailsDto simdetails, Errors errors){
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
	        	if(CustomerSimDetailsService.validateSimServiceNo(simdetails)) {
	        		if(CustomerSimDetailsService.isActive(simdetails)) {
	        			return ResponseEntity.ok("SIM already active");
	        		}
	        		else {
	        			return ResponseEntity.ok(CustomerSimDetailsService.getOffer(simdetails));
	        		}
	        	}
	        	else {
	        		ErrorsMessage error =new ErrorsMessage();
		        	error.setErrorCode(HttpStatus.NOT_FOUND.value());
		        	error.setMessage("Invalid details, please check again SIM number/Service number!");
		        	 return ResponseEntity.ok(error);
	        	}
	        }
		}
		
}

