package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resource_for_print_job")
@Getter
@Setter
public class ResourceForPrintJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceForPrintJobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "printJobId")
    
    private PrintJobs printJobs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourcePropertyDetailId")
    
    private ResourcePropertyDetail resourcePropertyDetail;
    //  ResourcePropertyDetailId int
    //  PrintJobId int
}

