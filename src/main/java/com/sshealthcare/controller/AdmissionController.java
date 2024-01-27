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
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Room;
import com.sshealthcare.service.AdmissionService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.RoomService;

@RestController
@RequestMapping("/admission")
@CrossOrigin(origins = {"http://localhost:3000"})

public class AdmissionController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AdmissionService admissionService;
	
	

	// adding admissions with roomId, patientId, DoctorId
	@PostMapping("/add/{rid}/{patientId}/{did}")
	public ResponseEntity<?> assignAdmission(@PathVariable("rid") int rid, @PathVariable("patientId") int patientId,
			@PathVariable("did") int did, @RequestBody Admission admission) {

		try {
			Room room = roomService.getById(rid);
	       

			 if (room.getType().equalsIgnoreCase("general") && admissionService.getSpecialPatients(rid) > 4  ) {
				 throw new InvalidIdException("General room not available");
		        }

		        
		        if (room.getType().equalsIgnoreCase("special") && admissionService.getSpecialPatients(rid) > 0) {
		            throw new InvalidIdException("Special room not available");
		        	
		        }

			// attach room to admission
			admission.setRoom(room);
			
			// fetch patient object  by patientId
			Patient patient = patientService.getone(patientId);
			// fetch patient object from DB by patientId

			
			// attach patient to admission
			admission.setPatient(patient);
		                                                                                                                                                                                                                                                                                        
			// fetch doctor object by doctorId
			Doctor doctor = doctorService.getById(did);
			
			// attach doctor to admission
			admission.setDoctor(doctor);
			
			// save the product in DB
			admission = admissionService.save(admission);
			
			return ResponseEntity.ok().body(admission);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}
	
	// getting all admissions
	@GetMapping("/all")
	public List<Admission> getAllAdmissions(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return admissionService.getAlladmissions(pageable);
	}

	// getting admissions by Id
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		// fetch admission object using given admissionId
		try {
			Admission admission = admissionService.getOne(id);
			return ResponseEntity.ok().body(admission);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
}








