package com.sefa.Health.service;

import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Hospital;
import com.sefa.Health.entity.Servicee;
import com.sefa.Health.exception.NotFoundException;
import com.sefa.Health.repository.DoctorRepository;
import com.sefa.Health.repository.HospitalRepository;
import com.sefa.Health.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceEntityService {

    private final ServiceRepository serviceRepository;
    private final DoctorRepository doctorRepository;

    private final HospitalRepository hospitalRepository;


    public Servicee addServiceToHospital(Long hospitalId, Servicee service) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElse(null);

        if (hospital != null) {
            service.setHospital(hospital);
            hospitalRepository.save(hospital);
            return serviceRepository.save(service);
        }

        return null; // Hastane bulunamadı
    }
    public Servicee addServiceToDoctor(Long doctorId, Servicee service) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        if (doctor != null) {
            service.setDoctor(doctor);
            doctorRepository.save(doctor);
            return serviceRepository.save(service);
        }

        return null; // Hastane bulunamadı
    }
    /*public Servicee addServiceToHospital(Long hospitalId, Servicee servicee) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        hospital.getServicees().add(servicee);
        serviceRepository.save(servicee);
        hospitalRepository.save(hospital);

        return  servicee;

    }*/



    public Servicee getServiceById(Long id){
        return serviceRepository.findById(id).
                orElseThrow(()-> new NotFoundException("No Service in this section"));

    }
    public void deleteServiceFromHospital(Long hospitalId,Long id){

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException("Hospital not found with id: " + hospitalId));

        List<Servicee> services = hospital.getServicees();

        Optional<Servicee> serviceToDelete = services.stream()
                .filter(service -> id.equals(service.getId()))
                .findFirst();

        if (serviceToDelete.isPresent()) {
            services.remove(serviceToDelete.get());
            hospitalRepository.save(hospital);
        } else {
            throw new NotFoundException("Service not found in the hospital with id: " + id);
        }


    }
    public void deleteServiceFromDoctor(Long doctorId,Long id){

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        List<Servicee> services = doctor.getDoctorServices();

        Optional<Servicee> serviceToDelete = services.stream()
                .filter(service -> id.equals(service.getId()))
                .findFirst();

        if (serviceToDelete.isPresent()) {
            services.remove(serviceToDelete.get());
            doctorRepository.save(doctor);
        } else {
            throw new NotFoundException("Service not found in the doctor with id: " + id);
        }


    }

    public Servicee changeImagePath(Long id,Servicee newService){

        Servicee servicee = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service not found with id: " + id));

        servicee.setImagePath(newService.getImagePath());
        serviceRepository.save(servicee);

        return servicee;

    }



}

