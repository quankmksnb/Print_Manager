package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.PrintJobStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "request_description_from_customer")
    private String requestDescriptionFromCustomer;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "image")
    private String image;

    @Column(name = "expected_end_date")
    private LocalDate expectedEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private PrintJobStatus printJobStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    Set<CustomerFeedback> customerFeedbacks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    Set<Delivery> deliveries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    Set<Design> designs;



}

