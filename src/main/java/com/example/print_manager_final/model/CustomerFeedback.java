package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "customer_feedback")
@Getter
@Setter
public class CustomerFeedback {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.feedbackTime = LocalDateTime.now();
    }

    @PreUpdate          // cập nhật time khi update
    protected void onUpdate() {
        this.responseTime = LocalDateTime .now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerFeedbackId;

    @Column(name = "feedback_content")
    private String feedbackContent;

    @Column(name = "response_by_company")
    private String responseByCompany;

    @Column(name = "feedback_time")
    private LocalDateTime feedbackTime;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userFeedbackId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    
    private Project project;


}
