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
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.Room;
import com.sshealthcare.service.AdmissionService;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientService;
import com.sshealthcare.service.RoomService;

@RestController
@RequestMapping("/admission")
public class AdmissionController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AdmissionService admissionService;
	
	@PostMapping("/add/{rid}/{patientId}/{did}")
	public ResponseEntity<?> assignAdmission(@PathVariable("rid") int rid,
			@PathVariable("patientId") int patientId, @PathVariable("did") int did,
			@RequestBody Admission admission) {

		try {
			Room room  = roomService.getById(rid);
			admission.setRoom(room);

			Patient patient = patientService.getById(patientId);
			admission.setPatient(patient);
			
			Doctor doctor = doctorService.getById(did);
			admission.setDoctor(doctor);
			
			admission = admissionService.assignAdmission(admission);
			return ResponseEntity.ok().body(admission);
		}   catch(InvalidIdException e) {
			return
					ResponseEntity.badRequest().body(e.getMessage());
			
}
		
	}

}
