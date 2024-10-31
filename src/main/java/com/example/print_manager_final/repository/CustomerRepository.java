package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
