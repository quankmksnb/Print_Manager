package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
