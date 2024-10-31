package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.ResourceForPrintJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceForPrintJobRepository extends JpaRepository<ResourceForPrintJob, Long> {
}
