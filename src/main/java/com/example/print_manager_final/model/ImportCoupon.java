package com.example.print_manager_final.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "import_coupon")
@Getter
@Setter
public class ImportCoupon {
    @PrePersist         // cập nhập time trước khi nó được lưu vào db
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @PreUpdate          // cập nhật time khi update
    protected void onUpdate() {
        this.updateTime = LocalDateTime .now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importCouponId;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    @Column(name = "trading_code")
    private String tradingCode;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resourcePropertyDetailId")
    private ResourcePropertyDetail resourcePropertyDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private User user;

    //  ResourcePropertyDetailId int
    //  EmployeeId int

}
