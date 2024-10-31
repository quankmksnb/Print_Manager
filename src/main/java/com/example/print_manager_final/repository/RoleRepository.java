package com.example.print_manager_final.repository;

import com.example.print_manager_final.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String name);
    public Role findByRoleCode(String code);
}
