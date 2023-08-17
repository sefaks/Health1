package com.sefa.Health.controller;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Servicee;
import com.sefa.Health.repository.ServiceRepository;
import com.sefa.Health.service.ServiceEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceEntityService serviceEntityService;


    @PostMapping("/add-to-hospital/{hospitalId}")
    public ResponseEntity<String> addServiceToHospital(@PathVariable Long hospitalId, @RequestBody Servicee service) {
        Servicee addedService = serviceEntityService.addServiceToHospital(hospitalId, service);

        if (addedService != null) {
            return ResponseEntity.ok("Service added to hospital successfully.");
        } else {
            return ResponseEntity.badRequest().body("Hospital not found.");
        }
    }

    @PostMapping("/add-to-doctor/{doctorId}")
    public ResponseEntity<String> addServiceToDoctor(@PathVariable Long doctorId, @RequestBody Servicee service) {
        Servicee addedService = serviceEntityService.addServiceToDoctor(doctorId, service);

        if (addedService != null) {
            return ResponseEntity.ok("Service added to doctor successfully.");
        } else {
            return ResponseEntity.badRequest().body("Doctor not found.");
        }
    }

    @DeleteMapping("/deleteFromHospital/{hospitalId}/{serviceId}")
    public ResponseEntity<String> deleteServiceFromHospital(@PathVariable Long hospitalId,
                                                            @PathVariable Long serviceId) {

        serviceEntityService.deleteServiceFromHospital(hospitalId, serviceId);
        return ResponseEntity.ok("Service deleted from hospital successfully.");
    }
    @DeleteMapping("/deleteFromDoctor/{doctorId}/{serviceId}")
    public ResponseEntity<String> deleteServiceFromDoctor(@PathVariable Long doctorId,
                                                            @PathVariable Long serviceId) {

        serviceEntityService.deleteServiceFromDoctor(doctorId, serviceId);
        return ResponseEntity.ok("Service deleted from doctor successfully.");
    }





}
