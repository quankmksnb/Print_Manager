package com.example.print_manager_final.dto.request;

import com.example.print_manager_final.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    String fullName;
    LocalDate dateOfBirth;
    String avatar;
    String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    String phoneNumber;

    Set<Role> roles;
}
