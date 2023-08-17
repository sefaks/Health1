package com.sefa.Health.repository;


import com.sefa.Health.entity.Doctor;
import com.sefa.Health.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findBySpecialityList(Speciality speciality);
}
