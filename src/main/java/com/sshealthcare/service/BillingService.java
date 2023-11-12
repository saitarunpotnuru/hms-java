package com.sshealthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Billing;
import com.sshealthcare.repository.BillingRepository;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;
	
	//adding 
	public Billing assignBilling(Billing billing) {
		
		return billingRepository.save(billing);
	}

	public List<Billing> getAllbillings(Pageable pageable) {
		return billingRepository.findAll(pageable).getContent();
	}

}
