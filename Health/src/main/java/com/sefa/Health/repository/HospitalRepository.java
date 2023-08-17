package com.sefa.Health.repository;

import com.sefa.Health.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    Optional<Hospital> findByHospitalId(Long hospitalId);
}
