package com.sshealthcare.controller;

import java.time.LocalTime;
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

import com.sshealthcare.dto.PatientDoctorDto;
import com.sshealthcare.enums.StatusType;
import com.sshealthcare.exception.InvalidIdException;
import com.sshealthcare.model.Doctor;
import com.sshealthcare.model.Patient;
import com.sshealthcare.model.PatientDoctor;
import com.sshealthcare.service.DoctorService;
import com.sshealthcare.service.PatientDoctorService;
import com.sshealthcare.service.PatientService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = { "http://localhost:3000","http://localhost:3000/doctor/appointment" })

public class PatientDoctorController {
	@Autowired
	private PatientDoctorService patientDoctorService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;

	// adding appointments
	@PostMapping("/add/{pid}/{did}")
	public ResponseEntity<?> insertAppointment(@PathVariable("pid") int pid, @PathVariable("did") int did,
			@RequestBody PatientDoctor patientDoctor) {
		try {

			Patient patient = patientService.getone(pid);
			patientDoctor.setPatient(patient);

			Doctor doctor = doctorService.getById(did);
			patientDoctor.setDoctor(doctor);

			LocalTime stime = doctor.getStartTime();
			LocalTime etime = doctor.getEndTime();

			if (patientDoctor.getTime().isBefore(stime) || patientDoctor.getTime().isAfter(etime)) {
				throw new InvalidIdException("slot not available");
			}
			patientDoctor.setStatus(StatusType.CANCELLED);

			patientDoctor = patientDoctorService.assignPatientDoctor(patientDoctor);
			return ResponseEntity.ok().body(patientDoctor);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	

	// get all appointments
	@GetMapping("/all")
	public List<PatientDoctor> getAllPatientDoctors(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return patientDoctorService.getAllpatientDoctors(pageable);
	}

	
	// get appointments by patientId
		@GetMapping("/get/appointment/{patientId}")
		public ResponseEntity<?> getAppointmentBy(@PathVariable("patientId") int patientId) {

			try {
				Patient patient = patientService.getAppointmentBy(patientId);
				List<PatientDoctor> list = patientDoctorService.getAll(patientId);
				return ResponseEntity.ok().body(list);
			} catch (InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
	
	
	
	

	

		// get appointments by doctorId
				@GetMapping("/get/{did}")
				public List<PatientDoctor> getAppointmentsByDoctorId(@PathVariable("did") int did) {
					return patientDoctorService.getAppointmentsByDoctorId(did);
				}
				        

	
	
	//updating prescriptions
	@PutMapping("/{pdid}")
	public ResponseEntity<?> updateAppointment(@PathVariable ("pdid")int pdid,
			@RequestBody PatientDoctorDto  patientDoctorDto) {
		
		try {
			PatientDoctor patientDoctor = patientDoctorService.getBypdId(pdid);
			System.out.println("entered put call");
			
			if (patientDoctorDto.getPrescriptionDetails() != null) {
                patientDoctor.setPrescriptionDetails(patientDoctorDto.getPrescriptionDetails());
            }
            if (patientDoctorDto.getFee() != null) {
                patientDoctor.setFee(patientDoctorDto.getFee());
            }
            if (patientDoctorDto.getTime() != null) {
                patientDoctor.setTime(patientDoctorDto.getTime());
            }
            if (patientDoctorDto.getDate() != null) {
                patientDoctor.setDate(patientDoctorDto.getDate());
            }
			patientDoctor = patientDoctorService.assignPatientDoctor(patientDoctor);
			return ResponseEntity.ok().body(patientDoctor);
			
		}catch(InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}