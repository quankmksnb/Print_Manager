package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "resource_property")
@Getter
@Setter
public class ResourceProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcePropertyId;

    @Column(name = "resource_property_name")
    private String resourcePropertyName;

    @Column(name = "quantity")
    private int quantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceProperty")
    Set<ResourcePropertyDetail> resourcePropertyDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourceId")
    
    private Resources resources;

}
