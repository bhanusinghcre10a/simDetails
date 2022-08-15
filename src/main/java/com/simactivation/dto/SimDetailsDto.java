package com.simactivation.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;


public class SimDetailsDto {
	final long serviceNumbermax=9999999999l;
	final long simNumbermax=9999999999999l;
	final long simNumbermin=1000000000000l;
	private int simId;
	@NotNull
	@Min(value=1000000000, message="not a valid serviceNumber no must be of length 10")
	@Max(value=serviceNumbermax, message="not a valid serviceNumber no must be of length 10")
	private long serviceNumber;
	@Min(value=simNumbermin, message="not a valid simNumber no must be of length 13")
	@Max(value=simNumbermax, message="not a valid simNumber no must be of length 13")
	private long simNumber;
	private String  simStatus;
	public SimDetailsDto() {
		
	}
	public SimDetailsDto(int simId, long serviceNumber, long simNumber, String simStatus) {
		super();
		this.simId = simId;
		this.serviceNumber = serviceNumber;
		this.simNumber = simNumber;
		this.simStatus = simStatus;
	}
	public int getSimId() {
		return simId;
	}
	public void setSimId(int simId) {
		this.simId = simId;
	}
	public long getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(long serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public long getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(long simNumber) {
		this.simNumber = simNumber;
	}
	public String getSimStatus() {
		return simStatus;
	}
	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}
	
}
