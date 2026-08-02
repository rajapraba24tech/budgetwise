package com.buget.tracker.repository;

import com.buget.tracker.model.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long> {
    Optional<BudgetPlan> findByUserId(Long userId);
}