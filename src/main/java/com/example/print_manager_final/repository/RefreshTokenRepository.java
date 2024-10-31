package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByToken(String token);
}
