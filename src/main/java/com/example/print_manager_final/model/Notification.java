package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notification")
@Getter
@Setter
public class Notification {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "ss_seen")
    private boolean isSeen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    
    private User user;

}

