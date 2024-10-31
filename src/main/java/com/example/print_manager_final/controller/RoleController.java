package com.example.print_manager_final.controller;

import com.example.print_manager_final.dto.request.UserCreateRequest;
import com.example.print_manager_final.dto.response.ApiResponse;
import com.example.print_manager_final.dto.response.UserResponse;
import com.example.print_manager_final.model.Role;
import com.example.print_manager_final.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ApiResponse<Role> addUser(@RequestBody Role role) {
        return ApiResponse.<Role>builder()
                .result(roleService.createRole(role))
                .code(200)
                .build();
    }

    @RequestMapping(value = "get_all_role", method = RequestMethod.GET)
    public ApiResponse<List<Role>> getAllRole() {
        return ApiResponse.<List<Role>>builder()
                .result(roleService.getAllRoles())
                .code(200)
                .build();
    }

    @RequestMapping(value = "delete_role", method = RequestMethod.DELETE)
    public ApiResponse<String> getAllRole(@RequestParam Long id) {
        roleService.deleteRole(id);
        return ApiResponse.<String>builder()
                .result("Deleted successfully")
                .code(200)
                .build();
    }
}
