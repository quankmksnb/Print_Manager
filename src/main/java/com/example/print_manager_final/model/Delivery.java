package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Column(name = "deliver_id")
    private int deliverId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "estimate_delivery_time")
    private LocalDate estimateDeliveryTime;

    @Column(name = "actual_delivery_time")
    private LocalDate actualDeliveryTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingMethodId")
    
    private ShippingMethod shippingMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    
    private Project project;


}

