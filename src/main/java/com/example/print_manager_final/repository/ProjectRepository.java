package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
