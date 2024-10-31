package com.example.print_manager_final.model;

import com.example.print_manager_final.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bill")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime .now(); // Cập nhật cả updateTime khi tạo mới
    }

    @PreUpdate          // cập nhật time khi update
    protected void onUpdate() {
        this.updateTime = LocalDateTime .now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "trading_code")
    private String tradingCode;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "bill_status")
    private BillStatus billStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    
    private Customer customer;
}