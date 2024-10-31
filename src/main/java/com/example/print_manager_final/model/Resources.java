package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.ResourceStatus;
import com.example.print_manager_final.enums.ResourceType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourcesId;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "image")
    private String image;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_status")
    private ResourceStatus resourceStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resources")
    
    Set<ResourceProperty> resourceProperties;

}

