package com.sshealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Admission;
import com.sshealthcare.model.Billing;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.service.AdmissionService;
import com.sshealthcare.service.BillingService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	@Autowired 
    private BillingService billingService;

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private AdmissionService admissionService;
    
    @Autowired
    private DoctorService doctorService;

    //adding bill with admissionId, patientId, doctorId
    @PostMapping("/add/{admissionId}/{pid}/{did}")
    public ResponseEntity<?> assignBilling(@PathVariable("admissionId") int admissionId,
    		@PathVariable("pid") int pid, @PathVariable("did") int did, 
    		@RequestBody Billing billing) {
    	
        try {
        	
        	Admission admission = admissionService.getById(admissionId);
            billing.setAdmission(admission);
            
            Patient patient = patientService.getById(pid);
            billing.setPatient(patient);
            
            Doctor doctor = doctorService.getById(did);
            billing.setDoctor(doctor);

    		billing = billingService.assignBilling(billing);

            return ResponseEntity.ok().body(billing);
        } catch (InvalidIdException e) {
            return 
            		ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
