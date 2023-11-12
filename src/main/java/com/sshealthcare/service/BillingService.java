package com.sshealthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Billing;
import com.sshealthcare.model.Receptionist;
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
	
	//getting Billings by Id
		public Billing getOne(int id) throws InvalidIdException {
			Optional<Billing> optional =  billingRepository.findById(id);
			if(!optional.isPresent()){
				throw new InvalidIdException("Billing ID Invalid");
			
		}
			return optional.get();
		
	}

		public Billing insertReceptionist(Billing billing) {
			return billingRepository.save(billing);
		}

}
