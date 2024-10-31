package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "resource_property_detail")
@Getter
@Setter
public class ResourcePropertyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcePropertyDetailId;

    @Column(name = "property_detail_name")
    private String propertyDetailName;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcePropertyDetail")
    
    Set<ImportCoupon> importCoupons;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcePropertyDetail")
    
    Set<ResourceForPrintJob> resourceForPrintJobs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyId")
    
    private ResourceProperty resourceProperty;

}
