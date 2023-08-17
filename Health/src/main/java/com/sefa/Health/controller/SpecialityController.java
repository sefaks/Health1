package com.sefa.Health.controller;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Hospital;
import com.sefa.Health.entity.Speciality;
import com.sefa.Health.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService specialityService;


    @PostMapping("/add")
    public ResponseEntity<String> addSpeciality(@RequestBody Speciality speciality){

        Speciality addedSpeciality = specialityService.createSpeciality(speciality);

        if (addedSpeciality != null) {
            return ResponseEntity.ok("Speciality added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Bad Request.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Speciality>> getAllHospital(){
        return ResponseEntity.ok(specialityService.viewSpecialities());
    }

    @DeleteMapping("/{specialityId}")
    public ResponseEntity<String> deleteSpeciality(@PathVariable Long specialityId) {
        specialityService.deleteSpeciality(specialityId);
        return ResponseEntity.ok("Speciality deleted successfully");
    }


}
