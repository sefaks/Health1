package com.sefa.Health.repository;

import com.sefa.Health.entity.Servicee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Servicee,Long> {
}
