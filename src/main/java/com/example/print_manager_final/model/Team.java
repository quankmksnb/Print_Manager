package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "team")
@Getter
@Setter
public class Team {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime .now(); // Cập nhật cả updateTime khi tạo mới
    }

    @PreUpdate          // cập nhật time khi update
    protected void onUpdate() {
        this.updateTime = LocalDateTime .now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_member")
    private int numberOfMember;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "manager_id")
    private int managerId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    
    Set<User> users;
}
