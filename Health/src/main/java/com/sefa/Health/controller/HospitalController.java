package com.sefa.Health.controller;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Hospital;
import com.sefa.Health.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;



    @PostMapping("/add")
    public ResponseEntity<Hospital> add(@RequestBody Hospital hospital) {
        return ResponseEntity.ok(hospitalService.addHospital(hospital));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hospital>> getAllHospital(){
        return ResponseEntity.ok(hospitalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id){
        return new ResponseEntity<>(hospitalService.getHospitalById(id), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHospital(@PathVariable(name ="id")  Long id){
        hospitalService.deleteHospital(id);
        return new ResponseEntity<>("Hospital delected succesfly.", HttpStatus.OK);

    }

    @GetMapping("/{hospitalId}/doctors")
    public ResponseEntity<List<Doctor>> getDoctorsInHospital(@PathVariable Long hospitalId) {
        List<Doctor> doctors = hospitalService.getDoctorsInHospital(hospitalId);

        if (doctors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(doctors);
    }




}
