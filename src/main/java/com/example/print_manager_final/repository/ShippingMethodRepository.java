package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod,Long> {
}
