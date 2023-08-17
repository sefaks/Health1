package com.sefa.Health.service;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Speciality;
import com.sefa.Health.exception.NotFoundException;
import com.sefa.Health.repository.DoctorRepository;
import com.sefa.Health.repository.SpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialityService {

    private final SpecialityRepository specialityRepository;
    private  final DoctorRepository doctorRepository;

    public Speciality createSpeciality(Speciality speciality) {

        Speciality speciality1 = new Speciality();
        speciality1.setName(speciality.getName());
        speciality1.setSpecialityId(speciality.getSpecialityId());
        return specialityRepository.save(speciality1);
    }

    public List<Speciality> viewSpecialities(){

        List<Speciality> specialities = specialityRepository.findAll();
        return specialities;
    }

    public void deleteSpeciality(Long specialityId){

        Speciality speciality = specialityRepository.findById(specialityId).orElseThrow(()
                -> new NotFoundException("No speciality with this id: " + specialityId));

        List<Doctor> doctorsWithSpeciality = doctorRepository.findBySpecialityList(speciality);
        for (Doctor doctor : doctorsWithSpeciality) {
            doctor.getSpecialityList().remove(speciality);
            doctorRepository.save(doctor);
        }

        specialityRepository.delete(speciality);

}









        }
