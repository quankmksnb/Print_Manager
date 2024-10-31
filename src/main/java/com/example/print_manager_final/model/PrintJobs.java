package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.PrintJobStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "print_jobs")
@Getter
@Setter
public class PrintJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long printJobsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "print_job_status")
    private PrintJobStatus printJobStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designId")
    
    private Design design;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "printJobs")
    
    Set<ResourceForPrintJob> resourceForPrintJobs;

}
