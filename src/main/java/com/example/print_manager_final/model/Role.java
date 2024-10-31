package com.example.print_manager_final.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UUID;


@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @PrePersist
    public void generateRoleCode() {
        this.roleCode = "ROLE_" + this.roleName.toUpperCase();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_name")
    private String roleName;

}

