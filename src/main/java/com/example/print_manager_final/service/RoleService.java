package com.example.print_manager_final.service;

import com.example.print_manager_final.exception.AppException;
import com.example.print_manager_final.exception.ErrorException;
import com.example.print_manager_final.model.Role;
import com.example.print_manager_final.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;

    @PreAuthorize("hasRole('admin')")
    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()) != null) {
            throw new AppException(ErrorException.ROLE_NAME_EXISTED);
        }
        return roleRepository.save(role);
    }

    @PreAuthorize("hasRole('admin')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PreAuthorize("hasRole('admin')")
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
