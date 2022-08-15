package com.simactivation.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simactivation.model.SimDetails;
import com.simactivation.model.SimOffers;

import com.simactivation.dto.SimDetailsDto;
import com.simactivation.repository.SimDetailsRepository;
import com.simactivation.repository.SimOffersRepository;

@Service
public class CustomerSimDetailsService {

	@Autowired
	private SimDetailsRepository SimDetailsRepository;
	@Autowired
	private SimOffersRepository SimOffersRepository;
	
	
	public boolean validateSimServiceNo(SimDetailsDto simDetails)
	{
		
		SimDetails customer=SimDetailsRepository.findByServiceNumber(simDetails.getServiceNumber());
		if(customer!=null && customer.getSimNumber()==(simDetails.getSimNumber())) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean isActive(SimDetailsDto simDetails)
	{
		
		SimDetails customer=SimDetailsRepository.findByServiceNumber(simDetails.getServiceNumber());
		if(customer.getSimStatus().equals("active")) {
			return true;
			
		}
		else {
			return false;
		}
	}
	public String getOffer(SimDetailsDto simDetails)
	{
		SimDetails customer=SimDetailsRepository.findByServiceNumber(simDetails.getServiceNumber());
		SimOffers offer=SimOffersRepository.findBySimId(customer.getSimId());
		return offer.toString();
	}
	
}