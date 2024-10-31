package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime .now();
        this.updateTime = LocalDateTime .now(); // Cập nhật cả updateTime khi tạo mới
    }

    @PreUpdate          // cập nhật time khi update
    protected void onUpdate() {
        this.updateTime = LocalDateTime .now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate dateOfBirth;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "email")
    private String email;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime  updateTime;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany
    Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<Bill> bills;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<ConfirmEmail> confirmEmails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<CustomerFeedback> customerFeedbacks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<Design> designs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<ImportCoupon> importCoupons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<KeyPerformanceIndicators> keyPerformanceIndicators;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<Notification> notifications;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<Project> projects;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    
    Set<RefreshToken> refreshTokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;
}
