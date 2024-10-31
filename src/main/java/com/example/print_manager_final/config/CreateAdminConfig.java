package com.example.print_manager_final.config;

import com.example.print_manager_final.model.Role;
import com.example.print_manager_final.model.User;
import com.example.print_manager_final.repository.RoleRepository;
import com.example.print_manager_final.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CreateAdminConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Creating Admin User");
        return args -> {
            Role adminRole = roleRepository.findByRoleName("admin");
            if (adminRole == null) {
                adminRole = Role.builder().roleName("admin").build();
                roleRepository.save(adminRole);
            }

            if (userRepository.findByUserName("admin").isEmpty()) {
                var roles = new HashSet<Role>();
                roles.add(adminRole);
                User user = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin1612"))
                        .roles(roles)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
