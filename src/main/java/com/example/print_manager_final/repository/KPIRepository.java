package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.KeyPerformanceIndicators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KPIRepository extends JpaRepository<KeyPerformanceIndicators, Long> {
}
