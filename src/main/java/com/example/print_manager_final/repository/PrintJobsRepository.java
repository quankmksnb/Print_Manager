package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.PrintJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintJobsRepository extends JpaRepository<PrintJobs, Long> {
}
