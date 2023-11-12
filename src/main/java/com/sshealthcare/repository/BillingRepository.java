package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sshealthcare.model.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer>{

}
