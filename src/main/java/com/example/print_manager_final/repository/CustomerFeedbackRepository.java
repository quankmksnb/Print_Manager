package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.CustomerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Long> {
}
