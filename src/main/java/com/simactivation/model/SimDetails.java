package com.simactivation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SimDetails")
public class SimDetails {
	
	@Id
	@GeneratedValue
	private int simId;
	private long serviceNumber;
	private long simNumber;
	private String  simStatus;
	public SimDetails() {
		
	}
	public SimDetails(int simId, long serviceNumber, long simNumber, String simStatus) {
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
