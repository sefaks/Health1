package com.sefa.Health.service;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Hospital;
import com.sefa.Health.entity.Servicee;
import com.sefa.Health.exception.NotFoundException;
import com.sefa.Health.repository.DoctorRepository;
import com.sefa.Health.repository.HospitalRepository;
import com.sefa.Health.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService  {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    private final ServiceRepository serviceRepository;


    public Hospital addHospital(Hospital hospital){

        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAll(){

        List<Hospital> hospitals = hospitalRepository.findAll();

        return hospitals;
    }
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).
                orElseThrow(()-> new NotFoundException("No Hospital in this section"));

    }

  /*  public void addDoctorToHospital(Long hospitalId, Long doctorId){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        hospital.getDoctorList().add(doctor);
        hospitalRepository.save(hospital);

    }*/




    public List<Doctor> getDoctorsInHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        List<Doctor> doctors = hospital.getDoctorList();

        if (doctors.isEmpty()) {
            System.out.println("There are no doctors in this hospital.");
            return Collections.emptyList();
        }

        return doctors;
    }

    public List<Servicee> getServicesInHospital(Long hospitalId){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        return hospital.getServicees();
    }
    public Hospital addServiceToHospital(Long hospitalId, Long serviceId) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        Servicee servicee = hospital.getServicees().stream().
                filter(s -> s.getServiceId().equals(serviceId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Service not found with id: " + serviceId));

        hospital.getServicees().add(servicee);
        hospitalRepository.save(hospital);

        return hospital;


    }

    public Hospital updateServicesInHospital(Long hospitalId,Long serviceId,Servicee newService) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        Servicee servicee = hospital.getServicees().stream().
                filter(s -> s.getServiceId().equals(serviceId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Service not found in the hospital with id: " + serviceId));

        servicee.setDescriptions(newService.getDescriptions());
        servicee.setImagePath(newService.getImagePath());
        servicee.setServiceName(newService.getServiceName());

        hospitalRepository.save(hospital);
        return hospital;

    }

    public Hospital updateHospitalInformations(Long hospitalId, Hospital newHospital){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        hospital.setServicees(newHospital.getServicees());
        hospital.setImagePath(newHospital.getImagePath());
        hospital.setDoctorList(newHospital.getDoctorList());
        hospital.setLocation(newHospital.getLocation());

        hospitalRepository.save(hospital);

        return hospital;
    }

    public void deleteHospital(Long hospitalId){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        hospitalRepository.delete(hospital);

    }




}
