package com.sshealthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshealthcare.model.Billing;
import com.sshealthcare.repository.BillingRepository;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;
	
	public Billing assignBilling(Billing billing) {
		
		return billingRepository.save(billing);
	}

}
