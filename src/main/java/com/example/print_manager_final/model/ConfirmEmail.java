package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "confirm_email")
@Getter
@Setter
public class ConfirmEmail {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long confirmEmailId;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "isConfirm")
    private boolean isConfirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

}

