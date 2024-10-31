package com.example.print_manager_final.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String userName;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    String fullName;

    @Email(message = "NOT_EMAIL")
    String email;
}
