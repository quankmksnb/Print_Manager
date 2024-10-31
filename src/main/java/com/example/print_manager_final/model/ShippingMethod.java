package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "shipping_method")
@Getter
@Setter
public class ShippingMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingMethodId;

    @Column(name = "shipping_method_name")
    private String shippingMethodName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shippingMethod")
    
    Set<Delivery> deliveries;
}

