package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
