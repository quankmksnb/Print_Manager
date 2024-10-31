package com.example.print_manager_final.dto.response;

import com.example.print_manager_final.dto.request.RoleRequest;
import com.example.print_manager_final.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long userId;
    String userName;
    String fullName;
    String email;
    String phoneNumber;

    Set<RoleRequest> roles;
}
