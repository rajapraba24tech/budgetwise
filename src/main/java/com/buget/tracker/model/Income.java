package com.buget.tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;

    private Long userId;
    private String source;
    private Double amount;
    private Integer month;
    private Integer year;
    private LocalDateTime createdAt;

    public Long getIncomeId() { return incomeId; }
    public Long getUserId() { return userId; }
    public String getSource() { return source; }
    public Double getAmount() { return amount; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setIncomeId(Long incomeId) { this.incomeId = incomeId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setSource(String source) { this.source = source; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}