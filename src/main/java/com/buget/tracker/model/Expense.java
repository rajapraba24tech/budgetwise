package com.buget.tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Long userId;
    private String categoryType;
    private Double amount;
    private String description;
    private LocalDate expenseDate;
    private Integer month;
    private Integer year;

    public Long getExpenseId() { return expenseId; }
    public Long getUserId() { return userId; }
    public String getCategoryType() { return categoryType; }
    public Double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public Integer getMonth() { return month; }
    public Integer getYear() { return year; }

    public void setExpenseId(Long expenseId) { this.expenseId = expenseId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setCategoryType(String categoryType) { this.categoryType = categoryType; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDescription(String description) { this.description = description; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }
    public void setMonth(Integer month) { this.month = month; }
    public void setYear(Integer year) { this.year = year; }
}