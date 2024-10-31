package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.Period;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "key_performance_indicators")
@Getter
@Setter
public class KeyPerformanceIndicators {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyPerformanceIndicatorsId;

    @Column(name = "indicator_name")
    private String indicatorName;

    @Column(name = "target")
    private int target;

    @Column(name = "actually_achieved")
    private int actuallyAchieved;

    @Column(name = "achieve_KPI")
    private boolean achieveKPI;

    @Enumerated(EnumType.STRING)
    private Period period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    
    private User user;
}
