package com.buget.tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "budget_plan")
public class BudgetPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    private Long userId;
    private Double monthlyExpensePct = 40.0;
    private Double weeklyExpensePct = 30.0;
    private Double wantsPct = 10.0;
    private Double savingsPct = 10.0;
    private Double bufferPct = 10.0;

    public Long getPlanId() { return planId; }
    public Long getUserId() { return userId; }
    public Double getMonthlyExpensePct() { return monthlyExpensePct; }
    public Double getWeeklyExpensePct() { return weeklyExpensePct; }
    public Double getWantsPct() { return wantsPct; }
    public Double getSavingsPct() { return savingsPct; }
    public Double getBufferPct() { return bufferPct; }

    public void setPlanId(Long planId) { this.planId = planId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setMonthlyExpensePct(Double v) { this.monthlyExpensePct = v; }
    public void setWeeklyExpensePct(Double v) { this.weeklyExpensePct = v; }
    public void setWantsPct(Double v) { this.wantsPct = v; }
    public void setSavingsPct(Double v) { this.savingsPct = v; }
    public void setBufferPct(Double v) { this.bufferPct = v; }
}