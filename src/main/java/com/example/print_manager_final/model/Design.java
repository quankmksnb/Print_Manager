package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.DesignStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "design")
@Getter
@Setter
public class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long designId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "design_time")
    private LocalDate designTime;

    @Column(name = "approver_id")
    private int approverId; // Người phê duyệt thiết kế

    @Enumerated(EnumType.STRING)
    @Column(name = "design_status")
    private DesignStatus designStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "design")
    Set<PrintJobs> printJobs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designerId")
    private User user;

}