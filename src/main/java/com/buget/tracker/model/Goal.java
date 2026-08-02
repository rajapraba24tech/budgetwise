package com.buget.tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    private Long userId;
    private String goalName;
    private Double targetAmount;
    private Double savedAmount = 0.0;
    private LocalDate deadline;

    public Long getGoalId() { return goalId; }
    public Long getUserId() { return userId; }
    public String getGoalName() { return goalName; }
    public Double getTargetAmount() { return targetAmount; }
    public Double getSavedAmount() { return savedAmount; }
    public LocalDate getDeadline() { return deadline; }

    public void setGoalId(Long goalId) { this.goalId = goalId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setGoalName(String goalName) { this.goalName = goalName; }
    public void setTargetAmount(Double targetAmount) { this.targetAmount = targetAmount; }
    public void setSavedAmount(Double savedAmount) { this.savedAmount = savedAmount; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}