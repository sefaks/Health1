package com.sefa.Health.service;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Hospital;
import com.sefa.Health.entity.Servicee;
import com.sefa.Health.entity.Speciality;
import com.sefa.Health.exception.NotFoundException;
import com.sefa.Health.repository.DoctorRepository;
import com.sefa.Health.repository.HospitalRepository;
import com.sefa.Health.repository.ServiceRepository;
import com.sefa.Health.repository.SpecialityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final HospitalRepository hospitalRepository;

    private final ServiceRepository serviceRepository;

    private final SpecialityRepository specialityRepository;


    public Doctor addDoctorToHospital(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);

        if (hospital != null) {
            doctor.setHospital(hospital);
            hospitalRepository.save(hospital);
            return doctorRepository.save(doctor);
        }

        return null; // Hastane bulunamadı
    }

    public void deleteDoctorFromHospital(Long hospitalId, Long id){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        Doctor doctor = hospital.getDoctorList().stream()
                .filter(d -> id.equals(d.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Doctor not found in the hospital with id: " + id));

        hospital.getDoctorList().remove(doctor);
        doctorRepository.save(doctor);
        hospitalRepository.save(hospital);
    }


    public List<Doctor> getAll(){

        List<Doctor> doctors = doctorRepository.findAll();

        return doctors;
    }

    public void deleteDoctorFromApp(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + id));

        Hospital hospital = doctor.getHospital();

        doctorRepository.delete(doctor);
        hospital.getDoctorList().remove(doctor);

    }
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Doctor not found with id: " + id));

    }

    public Doctor assignSpecialityToDoctor(Long doctorId, Long specialityId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        Speciality speciality = specialityRepository.findById(specialityId)
                .orElseThrow(() -> new NotFoundException("Speciality not found with id: " + specialityId));

        doctor.getSpecialityList().add(speciality);
        return doctorRepository.save(doctor);
    }

    public List<Servicee> viewDoctorServices(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        return doctor.getDoctorServices();

    }
    public String viewDoctorInformations(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        return doctor.getInformations();

    }
    public Doctor updateDoctorInformations(Long doctorId, String newInformation) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        doctor.setInformations(newInformation);
        doctorRepository.save(doctor);

        return doctor;

    }







}

