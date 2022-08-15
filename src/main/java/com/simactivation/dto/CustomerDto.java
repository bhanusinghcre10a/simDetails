package com.simactivation.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDto {
	
	private long uniqueIdNumber;
	@NotNull
	private LocalDate dateOfBirth;
	@Email
	//@NotNull
	private String emailAddress;
	//@Pattern(regexp = "[a-z][A-Z]{15}",message="invalid first name ")
	private String firstName;
	//@Pattern(regexp = "[a-z][A-Z]{15}",message="invalid last name")
	private String  lastName;
	private String idType;
	private int  customerAddress_addressId;
	private int simId;
	private String state;
	public CustomerDto() {
		
	}
	public CustomerDto(long uniqueIdNumber, LocalDate dateOfBirth, String emailAddress, String firstName, String lastName,
			String idType, int customerAddress_addressId, int simId, String state) {
		super();
		this.uniqueIdNumber = uniqueIdNumber;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idType = idType;
		this.customerAddress_addressId = customerAddress_addressId;
		this.simId = simId;
		this.state = state;
	}

	public long getUniqueIdNumber() {
		return uniqueIdNumber;
	}

	public void setUniqueIdNumber(long uniqueIdNumber) {
		this.uniqueIdNumber = uniqueIdNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfbirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public int getCustomerAddress_addressId() {
		return customerAddress_addressId;
	}

	public void setCustomerAddress_addressId(int customerAddress_addressId) {
		this.customerAddress_addressId = customerAddress_addressId;
	}

	public int getSimId() {
		return simId;
	}

	public void setSimId(int simId) {
		this.simId = simId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
