package com.simactivation.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class CustomerIdentityDto {
	final long uniqueIdmax=9999999999999999l;
	final long uniqueIdmin=1000000000000000l;
	@NotNull(message = "Please enter id")
	@Min(value=uniqueIdmin, message="not a valid uniqueIdNumber  must be of length 16")
	@Max(value=uniqueIdmax, message="not a valid uniqueIdNumber must be of length 16")
	private long uniqueIdNumber;
	@NotNull
	private LocalDate dateOfBirth;
	private String firstName;
	private String  lastName;
	private String emailAddress;
	private String state;
	public CustomerIdentityDto() {
		
	}
	public CustomerIdentityDto(long uniqueIdNumber, LocalDate dateOfbirth, String firstName, String lastName,
			String emailAddress, String state) {
		super();
		this.uniqueIdNumber = uniqueIdNumber;
		this.dateOfBirth = dateOfbirth;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
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
	public void setDateOfBirth(LocalDate dateOfbirth) {
		this.dateOfBirth = dateOfbirth;
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
