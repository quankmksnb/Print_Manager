package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String username);
    Optional<User> findByUserName(String username);
    boolean existsByEmail(String email);
}
