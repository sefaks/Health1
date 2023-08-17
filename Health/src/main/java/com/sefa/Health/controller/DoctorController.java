package com.sefa.Health.controller;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @PostMapping("/add-to-hospital/{hospitalId}")
    public ResponseEntity<String> addDoctorToHospital(@PathVariable Long hospitalId,@RequestBody Doctor doctor) {
        Doctor addedDoctor = doctorService.addDoctorToHospital(hospitalId, doctor);

        if (addedDoctor != null) {
            return ResponseEntity.ok("Doctor added to hospital successfully.");
        } else {
            return ResponseEntity.badRequest().body("Hospital not found.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctor() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.getDoctorById(id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorService.deleteDoctorFromApp(id);
        return new ResponseEntity<>("Doctor deleted successfully.", HttpStatus.OK);

    }
    @DeleteMapping("/delete-doctor/{hospitalId}/{id}")
    public ResponseEntity<String> deleteDoctorFromHospital(@PathVariable Long hospitalId,@PathVariable Long id) {
        doctorService.deleteDoctorFromHospital(hospitalId,id);
        return new ResponseEntity<>("Doctor deleted successfully.", HttpStatus.OK);

    }
    @PostMapping("/{doctorId}/assign-speciality/{specialityId}")
    public ResponseEntity<Doctor> assignSpecialityToDoctor(@PathVariable Long doctorId,@PathVariable Long specialityId) {

        Doctor doctor = doctorService.assignSpecialityToDoctor(doctorId, specialityId);
        return ResponseEntity.ok(doctor);
    }
}