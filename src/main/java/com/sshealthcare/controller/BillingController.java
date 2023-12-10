package com.sshealthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Admission;
import com.sshealthcare.model.Billing;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Receptionist;
import com.sshealthcare.service.AdmissionService;
import com.sshealthcare.service.BillingService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientService;

@RestController
@RequestMapping("/billing")
@CrossOrigin(origins = {"http://localhost:3000"})

public class BillingController {

	@Autowired
	private BillingService billingService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private AdmissionService admissionService;

	@Autowired
	private DoctorService doctorService;

	// adding bill with admissionId, patientId, doctorId
	@PostMapping("/add/{admissionId}/{pid}/{did}")
	public ResponseEntity<?> assignBilling(@PathVariable("admissionId") int admissionId, @PathVariable("pid") int pid,
			@PathVariable("did") int did, @RequestBody Billing billing) {

		try {

			Admission admission = admissionService.getById(admissionId);
			billing.setAdmission(admission);

			Patient patient = patientService.getOne(pid);
			billing.setPatient(patient);

			Doctor doctor = doctorService.getById(did);
			billing.setDoctor(doctor);

			billing = billingService.assignBilling(billing);

			return ResponseEntity.ok().body(billing);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// getting all billings
	@GetMapping("/all")
	public List<Billing> getAllBillings(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return billingService.getAllbillings(pageable);
	}

	
	
	
	// getting billings by Id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Billing billing = billingService.getOne(id);
			return ResponseEntity.ok().body(billing);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	// update billings
	@PutMapping("/update/{id}") // :update: which record to update? give me new value for update
	public ResponseEntity<?> updateBilling(@PathVariable("id") int id, @RequestBody Billing newBilling) {
		try {
			// validate id
			Billing Billing = billingService.getOne(id);
			if (newBilling.getBillAmount() != 0)
				Billing.setBillAmount(newBilling.getBillAmount());
			if (newBilling.getPaymentStatus() != null)
				Billing.setPaymentStatus(newBilling.getPaymentStatus());
			Billing = billingService.insertReceptionist(Billing);
			return ResponseEntity.ok().body(Billing);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
