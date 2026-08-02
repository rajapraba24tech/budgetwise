package com.buget.tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private Long userId;
    private String billName;
    private Double amount;
    private Integer dueDay;
    private String category;

    public Long getBillId() { return billId; }
    public Long getUserId() { return userId; }
    public String getBillName() { return billName; }
    public Double getAmount() { return amount; }
    public Integer getDueDay() { return dueDay; }
    public String getCategory() { return category; }

    public void setBillId(Long billId) { this.billId = billId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setBillName(String billName) { this.billName = billName; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDueDay(Integer dueDay) { this.dueDay = dueDay; }
    public void setCategory(String category) { this.category = category; }
}