package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
