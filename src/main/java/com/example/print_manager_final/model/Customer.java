package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    Set<Bill> bills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    Set<Delivery> deliveries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    Set<Project> projects;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    Set<CustomerFeedback> customerFeedbacks;
}
