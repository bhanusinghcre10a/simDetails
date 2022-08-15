package com.simactivation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


public class CustomerAddressDto {
	private int addressId;
	@Pattern(regexp = "^.{0,25}$",message="Address should be maximum of 25 characters")
	private String address;
	@Pattern(regexp = "[a-z][A-Z]",message="City/State should not contain any special characters except space")
	private String city;
	@Min(value=100000, message="not a valid pin no must be of length 6")
	@Max(value=999999, message="not a valid pin no must be of length 6")
	private long pincode;
	@Pattern(regexp = "[a-z][A-Z]",message="City/State should not contain any special characters except space")
	private String  state;
	public CustomerAddressDto() {
		
	}
	public CustomerAddressDto(int addressId, String address, String city, long pincode, String state) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
